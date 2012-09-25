require 'ant'
require 'yaml'
require 'ftools'

@@includeantruntime = false
compileVerbose = false

sep = File::Separator

@@debug =  "on"
debuglevel="lines,source,vars"
encoding = "utf8"

timestart = Time.new  

config = YAML::load_file("build_config.yaml")

#Override default values or add new ones from hosts's file:

host = `hostname`.strip

specific_file = host.empty? ? "can't get host name" : host

host_specific_config_file = "build_config_"+specific_file+".yaml"

if (File.exist?(host_specific_config_file))
    YAML::load_file(host_specific_config_file).each {
        |key, value| config.store(key, value)
    }
else
    puts host_specific_config_file + " does not exist."
end

src_dir = config['src_dir']
ant_home = config['ant_home']
test_dir = config['test_dir']
configuration_dir = config['configuration_dir']
artifact_version = config['artifact_version']


build_dir = config['build_dir']
libDir = build_dir+sep+'lib'
config_to_deploy_dir = build_dir+sep+'lib'
compileLib_dir = libDir+sep+'compile'
testLib_dir = libDir+sep+'test'

artifact_file = config['artifact_file']
@@javac_max_mem = config['javac_max_mem']

@@java_version =  "1.7"

@@debug_port = config['debug_port']
  
#locations of tools
@@java_home = config['java_home'] == nil ? ENV['JAVA_HOME'] : config['java_home'] #if there's no java home defined in config, look for an environment JAVA_HOME variable
puts "Java Home: "+ @@java_home

#specify relative temporary build destination dir. paths
classes_to_deploy_dir = build_dir+sep+"classes"
test_class_dir = build_dir+sep+"tests"

@@test_output_dir = build_dir+sep+"test-output" 

@@testNGFileName = "testNg-suite.xml"

#Will add all jars in dir to a new fileset
def addJars(dir)
  fileset :dir =>dir do
    include :name => "**/*.jar"
  end 
end

#Will compile the java in src_dir, using the classpath specified by build_classpath, to classes in the deploy_dir
def compileSrc(deploy_dir, build_classpath, src_dir)
  ant.javac( :memoryMaximumSize => @@javac_max_mem, :fork => "yes", :executable => @@java_home+"\\bin\\javac", :target =>@@java_version, :debug => @@debug, :destdir => deploy_dir, :includeantruntime => @@includeantruntime) do
     classpath :refid => build_classpath
     src { pathelement :location => src_dir }
   end
end

#Will run tests specified by the group name. debugFlag controls addition of the JVM debug options (added if true)
def runTests(groupnames, debugFlag)
  ant.testng(
    :jvm => @@java_home+"\\bin\\javac"
    :classpathref => "testing.classpath",
    :outputDir => @@test_output_dir, 
    :haltOnfailure => "true", 
    :testnames => groupnames) do      
      if (!debugFlag.nil? and debugFlag == "true")
        jvmarg(:value => "-Xdebug")
        jvmarg(:value => "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address="+@@debug_port)
      end
      xmlfileset(:dir => '.', :includes => @@testNGFileName)
   end
end

desc "Deletes and recreates temporary build directories."
task :clean do
  rm_rf build_dir

  sleep(2)
  mkdir_p build_dir
  mkdir_p classes_to_deploy_dir

  puts build_dir+" created."
end

namespace :ivy do

    task :setup do
        ant.path :id => 'ivy.lib.path' do
            fileset :dir => ant_home + sep +'lib', :includes => 'ivy-2.2.0.jar'
        end
    end

    task :taskdef => :setup do
        ant.taskdef :resource => "org/apache/ivy/ant/antlib.xml", :classpathref => "ivy.lib.path"
    end
end

desc "Resolve dependencies specified in ivy.xml"
task :resolveDependencies => "ivy:taskdef" do
   ant.settings(:file =>"ivysettings.xml")
   ant.retrieve(:pattern =>libDir+"/[conf]/[artifact].[ext]", :type =>"jar,bundle", :conf => "compile, test")
end

desc "Creates standard classpath references used by source and jsp compilation. Not much good by itself!"
task :setup => [:clean, :resolveDependencies] do
  ant.path(:id => "deploy.lib.classpath") do
    addJars(compileLib_dir)
  end

   #Copy config files to build/deploy directory
   config_files = Dir.glob(configuration_dir+sep+"{*.xml,*.yaml}")
   FileUtils.cp_r config_files, config_to_deploy_dir

end

multitask :multiSetup => [:setup, :clean] 

desc "Compile the java server code"
task :compile => :multiSetup do
  
  compileSrc(classes_to_deploy_dir, "deploy.lib.classpath", src_dir)
   
  puts "Java src compiled to "+classes_to_deploy_dir
  
end

desc "Creates the classpath references, copies test resources, modifies the testNG-suite.xml and compiles the tests"
task :test_setup => :setup do
  mkdir_p test_class_dir
  mkdir_p @@test_output_dir
  
  ant.taskdef(:resource => "testngtasks", :classpath => testLib_dir+sep+"testng.jar")

  #path for compiling tests
  ant.path(:id => "test.compile.classpath") do
    path :refid =>  "deploy.lib.classpath"
    pathelement :location => classes_to_deploy_dir
    addJars(testLib_dir)
  end

  compileSrc(test_class_dir, "test.compile.classpath", test_dir)

  #replace tokens in testNg-suite.xml
  testNG_path = @@testNGFileName
  testNGFile = File.read testNG_path  
  testNGFile.gsub! /@rake_build@/, "true"
  File.open testNG_path, "w" do |f| f.write testNGFile end
  
  #path for running tests
    ant.path(:id => "testing.classpath") do
      path :refid =>  "test.compile.classpath"
      pathelement :location => "build" #to ensure yaml config files are found
      pathelement :location => config_to_deploy_dir #to ensure redis configuration is found
      pathelement :location => test_class_dir
      pathelement :location => testLib_dir
    end
         
end  

desc "Runs the unit tests (i.e. those tests in the Unit-Tests group). Use rake run_unit_tests[true] if debugging is required (debug port is 8000)"
task :run_unit_tests, [:debug] => [:compile, :test_setup] do |t, args|
  args.with_defaults(:debug => false)
  debugFlag = args[:debug]
  runTests "Unit-Tests", debugFlag
end

desc "Runs the integration tests (i.e. those tests in the Integration-Tests group). Use rake run_integration_tests[true] if debugging is required (debug port is 8000)"
task :run_integration_tests, [:debug] => [:compile, :test_setup] do |t, args|
  puts "Running integration tests now"
  args.with_defaults(:debug => false)
  debugFlag = args[:debug]
  runTests "Integration-Tests", debugFlag
end

desc "Builds the artifacts"
task :buildJar => [:run_unit_tests ] do
  if (File.exist?(artifact_file))
    rm artifact_file
    artifact_file = File.new(artifact_file)
  end
  
  test_exclusion_patterns = "test-output/**,tests/**,testLib/**"

  artifactName = File.basename(artifact_file)

  ant.jar(
    :destfile => artifact_file, 
    :basedir => build_dir+sep+"classes",
    :excludes =>artifactName+",build/**,**/.git/**,**/.*,**/.*/**,build.xml,WEB-INF/generated_web.xml,"+test_exclusion_patterns)
  
end



desc "Creates and adds a jar to the specified repository (times it, too)"
task :default => [:buildJar, "ivy:taskdef"] do
  puts "Build time: "+(Time.now - timestart).to_s

  ant.publish(
       :pubrevision => artifact_version,
       :status => "release",
       :resolver => "internal",
       :update => "true",
       :overwrite => "true") do
          artifacts :pattern => File.dirname(artifact_file)+"/[artifact].[ext]"
      end

end
