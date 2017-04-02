package org.apache.struts.example.crud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.jboss.logging.Logger;

public abstract class JpaSupport<T> {
    private static Logger logger = Logger.getLogger(JpaSupport.class);

    EntityManagerFactory factory;
    EntityManager manager;

    @SuppressWarnings("rawtypes")
    private Class clazz;

    @SuppressWarnings("rawtypes")
    public JpaSupport(Class clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        String entity = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);

        if (manager == null) {
            logger.warn("manager == null");
            factory = EntityManagerSession.getInstance().getEntityManagerFactory("struts2-crud-example");
            if (factory != null) {
                manager = factory.createEntityManager();
            } else {
                logger.warn("fatory == null");
                return new ArrayList<T>();
            }
        }
        
        String query = "from " + entity + " e";
        logger.info("query = " + query);
        Query q = manager.createQuery(query);
        return q.getResultList();
    }
}