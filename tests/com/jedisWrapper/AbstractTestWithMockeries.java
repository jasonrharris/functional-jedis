package com.jedisWrapper;

import org.jmock.Mockery;
import org.testng.annotations.AfterClass;

import java.util.ArrayList;
import java.util.List;

/**
 * An Abstract convenience class for creating tests easily and that verifies contexts afterwards
 * User: jasonhome
 * Date: 10/01/2012
 * Time: 16:50
 */
public abstract class AbstractTestWithMockeries {
    protected final List<Mockery> contexts = new ArrayList<Mockery>();

    @AfterClass
    public void verify(){
        for(Mockery context : contexts){
            context.assertIsSatisfied();
        }
    }

    protected Mockery createContext() {
        Mockery context = new Mockery();
        contexts.add(context);
        return context;
    }
}
