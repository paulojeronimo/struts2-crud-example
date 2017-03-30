package org.apache.struts.example.crud.interceptors;

import java.lang.reflect.Field;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManagerFactory;

import org.apache.struts.example.crud.annotations.PersistenceUnitConfiguration;
import org.apache.struts.example.crud.dao.EntityManagerSession;
import org.jboss.logging.Logger;

public class BaseInterceptor {

    private static final Logger LOG = Logger.getLogger(BaseInterceptor.class);
    private static final String UNDERLINE = "_";

    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws Exception {
        try {
            Object bean = invocationContext.getTarget();
            Class<?> clazz = bean.getClass();
            LOG.debug("clazz: " + clazz.getName());
            if (clazz.getName().contains(UNDERLINE)) {
                clazz = Class.forName(clazz.getName().split(UNDERLINE)[0]);
            }
            PersistenceUnitConfiguration unitConfiguration = (PersistenceUnitConfiguration) clazz
                    .getAnnotation(PersistenceUnitConfiguration.class);
            Field fieldUnitEntityManager = clazz.getDeclaredField(unitConfiguration.entityManagerField());
            EntityManagerSession.getInstance().putEntityManagerFactory(unitConfiguration.persistenceUnitName(),
                    (EntityManagerFactory) fieldUnitEntityManager.get(bean));
            LOG.debug("EntityManagerFactory " + unitConfiguration.persistenceUnitName() + " added to EntityManagerSession");
            return invocationContext.proceed();
        } catch (Exception ex) {
            LOG.error(ex);
            throw ex;
        }
    }

}