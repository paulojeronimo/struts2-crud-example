package org.apache.struts.example.crud.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

public class ServiceLocator {
    private static ServiceLocator serviceLocator;
    private static final Logger LOG = Logger.getLogger(ServiceLocator.class);

    private ServiceLocator() {
    }

    public static ServiceLocator getServiceLocator() {
        if (serviceLocator == null)
            serviceLocator = new ServiceLocator();
        return serviceLocator;
    }

    private Object lookupLocal(final Class<?> ejbClass) {
        String className = "org.apache.struts.example.crud.service.Default" + ejbClass.getSimpleName();
        Object o = null;
        try {
            o = Class.forName(className).newInstance();
            LOG.warn("Using " + className + " implementation");
        } catch (Throwable t) {
            LOG.error("lookupLocal exception", t);
            LOG.error("Service " + className + " not found! Kick the programmer ass!");
        }
        return o;
    }

    public Object lookup(final Class<?> ejbClass) {
        String className = "java:module/" + ejbClass.getSimpleName() + "!"
                + "org.apache.struts.example.crud.service." + ejbClass.getSimpleName();
        Object o = null;
        try {
            final Hashtable<String, String> jndiProperties = new Hashtable<>();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            final Context context = new InitialContext(jndiProperties);
            o = context.lookup(className);
            LOG.info("Using " + className + " implementation");
        } catch (Throwable e) {
            LOG.debug("lookup exception", e);
            LOG.warn("Service " + className + " not found! Trying default implementation ...");
            o = lookupLocal(ejbClass);
        }
        return o;
    }
}