package com.jedisWrapper.configuration;

import com.jedisWrapper.ids.CodedRuntimeException;
import com.jedisWrapper.ids.JedisErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Loads Yaml application configurations. A concrete implementation should be
 * provided by the Application as required.
 * User: jasonhome
 * Date: 11/01/2012
 * Time: 12:52
 */
@SuppressWarnings("UnusedDeclaration")
public class ConfigurationLoader<A extends ApplicationConfiguration> implements Configuration<A> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodedRuntimeException.class);

    public enum ConfigurationFile {
        REDIS_SERVERS("redis-servers"),
        HOST_CONFIG("config");

        public static final char NAME_SEPARATOR = '_';
        private static final String YAML = ".yaml";
        private final String fileName;

        private ConfigurationFile(String fileName) {
            this.fileName = fileName;
        }

        private String getFilePath(String differentiator) {
            return fileName + NAME_SEPARATOR + differentiator + YAML;
        }

    }
    private final String hostname;

    private final Environment environment;
    private final A applicationConfiguration;
    private final Set<RedisConfiguration> redisServerConfigurations;

    public Environment getEnvironment() {
        return environment;
    }

    public Set<RedisConfiguration> getRedisServerConfigurations() {
        return redisServerConfigurations;
    }

    public String getHostname() {
        return hostname;
    }

    //Called by Guice
    @SuppressWarnings("UnusedDeclaration")
    public ConfigurationLoader(ConfigurationBeanDetails applicationConfigurationBeanDetails) {

        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new CodedRuntimeException("Could not get a hostname", JedisErrorCode.CONFIGURATION_ERROR);
        }

        this.applicationConfiguration = this.<A>loadConfig(
                ConfigurationFile.HOST_CONFIG.getFilePath(hostname),
                applicationConfigurationBeanDetails).get(0);

        this.environment = applicationConfiguration.getEnvironment();

        String redisConfigFilePath = ConfigurationFile.REDIS_SERVERS.getFilePath(environment.subName());

        if (getFileAsInputStream(redisConfigFilePath, false) == null){
            LOGGER.info("No environment specific Redis configuration available. Attempting host specific ...");
            redisConfigFilePath = ConfigurationFile.REDIS_SERVERS.getFilePath(hostname);
        }

        final List<RedisConfigurationBean> redisConfigurationBeans = loadConfig(
                redisConfigFilePath,
                Tag.REDIS_SERVER_BEAN,
                Tag.REDIS_SERVER_CONFIG);
        
        this.redisServerConfigurations = new HashSet<RedisConfiguration>();
        
        for(RedisConfigurationBean redisConfigBean : redisConfigurationBeans){
            redisServerConfigurations.add(new RedisConfiguration(redisConfigBean));                                                                            
        }
        
    }

    public A getApplicationConfiguration() {
        return applicationConfiguration;
    }

    private <C> List<C> loadConfig(String configFilePath, ConfigurationBeanDetails... configurationTag) {
        InputStream input = getFileAsInputStream(configFilePath, true);

        final List<C> cs = new ArrayList<C>();
        try {
            
            Constructor constructor = new Constructor();
            for (ConfigurationBeanDetails tag : configurationTag){
                constructor.addTypeDescription(new TypeDescription(tag.getBeanClass(), tag.getTagName()));
            }
            
            for(Object object : new Yaml(constructor).loadAll(input)){
                //cast will be fine if config file's contents are correctly specified
                //noinspection unchecked
                cs.add((C)object);
            }
        }
        catch (ClassCastException cce){
            StringBuilder configurationTags = new StringBuilder();
            for (ConfigurationBeanDetails tag : configurationTag){
                configurationTags.append(tag.getClass());
                configurationTags.append(", ");
            }
            throw new CodedRuntimeException(
                    "Could not load: "+ configFilePath +" as it did not specify classes of type "+ configurationTags.toString(),
                    JedisErrorCode.CONFIGURATION_ERROR);
        }
        return cs;
    }

    private InputStream getFileAsInputStream(String configFilePath, boolean b) {
        InputStream input = ClassLoader.getSystemResourceAsStream(configFilePath);

        if (input == null && b){
            StringBuilder classPath = new StringBuilder();
            for (String entry : System.getProperty("java.class.path").split(System.getProperty("path.separator"))){
                classPath.append(entry);
                classPath.append("\t\n");
            }
            throw new CodedRuntimeException(
                    "Could not locate "+configFilePath + " in \n\t"+ classPath.toString(),
                    JedisErrorCode.CONFIGURATION_ERROR);
        }
        return input;
    }


}
