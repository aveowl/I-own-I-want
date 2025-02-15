package com.iowniwant.controller.helper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class InitialContextFactoryMock implements InitialContextFactory {
    private static Context context;

    static {
        try {
            context = new InitialContext(true) {
                Map<String, Object> bindings = new HashMap<>();

                @Override
                public void bind(String name, Object obj) {
                    bindings.put(name, obj);
                }

                @Override
                public Object lookup(String name) {
                    return bindings.get(name);
                }
            };
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void bind(String name, Object obj) {
        try {
            context.bind(name, obj);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) {
        return context;
    }
}
