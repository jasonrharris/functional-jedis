package com.jedisWrapper.jedis;

import com.jedisWrapper.AbstractTestWithMockeries;
import com.jedisWrapper.TestGroups;
import com.jedisWrapper.functors.JedisCommandsFunctor;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test that functor is executed
 * User: jasonhome
 * Date: 10/12/2011
 * Time: 08:13
 */
@Test(groups = TestGroups.UNIT)
public class JedisClientFunctorExecutorTest extends AbstractTestWithMockeries {
    
    
    public void shouldExecuteFunctor(){
        Mockery context = createContext();

        final MockedCommandsAndExecutor commandsAndExecutor = new MockedCommandsAndExecutor(context);

        @SuppressWarnings("unchecked") 
        final JedisCommandsFunctor<String, String, AllJedisCommands> functor = context.mock(JedisCommandsFunctor.class);
          
        final String aParameter = "aParameter";
        final String anOutput = "anOutput";
        
        context.checking(new Expectations(){{
            
            oneOf(functor).execute(commandsAndExecutor.getJedisCommands(), aParameter);
            will(returnValue(anOutput));
            
        }});

        JedisClientFunctorExecutor<AllJedisCommands> executor = commandsAndExecutor.getExecutor();
        assertThat(executor.executeFunction(functor, aParameter), equalTo(anOutput));

    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Thrown by Jedis")
    public void shouldStillReturnResourcesIfExceptionThrown(){
            Mockery context = createContext();

            final MockedCommandsAndExecutor commandsAndExecutor = new MockedCommandsAndExecutor(context);

            @SuppressWarnings("unchecked")
            final JedisCommandsFunctor<String, String, AllJedisCommands> functor = context.mock(JedisCommandsFunctor.class);

            final String aParameter = "aParameter";

            context.checking(new Expectations(){{

                oneOf(functor).execute(commandsAndExecutor.getJedisCommands(), aParameter);
                will(throwException(new RuntimeException("Thrown by Jedis")));

            }});

            JedisClientFunctorExecutor<AllJedisCommands> executor = commandsAndExecutor.getExecutor();
            executor.executeFunction(functor, aParameter);

        }

    private class MockedCommandsAndExecutor {
        private final AllJedisCommands jedisCommands;
        private final JedisClientFunctorExecutor<AllJedisCommands> executor;
        private final JedisClient<AllJedisCommands> jedisClient;

        public MockedCommandsAndExecutor(Mockery context) {
            //noinspection unchecked
            jedisClient = context.mock(JedisClient.class);
            jedisCommands = context.mock(AllJedisCommands.class);

            context.checking(new Expectations() {{
                oneOf(jedisClient).getJedis();
                will(returnValue(jedisCommands));

                oneOf(jedisClient).returnResource(jedisCommands);
            }});

            executor = new JedisClientFunctorExecutor<AllJedisCommands>(jedisClient);
        }

        public JedisClientFunctorExecutor<AllJedisCommands> getExecutor() {
            return executor;
        }

        public AllJedisCommands getJedisCommands() {
            return jedisCommands;
        }
    }
}
