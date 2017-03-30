package org.apache.struts.example.crud.dao;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;

public final class EntityManagerSession {

    private static EntityManagerSession instance;
    private ThreadLocal<HashMap<String, EntityManagerFactory>> entityManagerThread = new ThreadLocal<HashMap<String, EntityManagerFactory>>();
    private Map<String, EntityManagerFactory> entityManagerFactory = new HashMap<String, EntityManagerFactory>();

    private EntityManagerSession() {
    }

    public static EntityManagerSession getInstance() {
        if (instance == null) {
            instance = new EntityManagerSession();
        }
        return instance;
    }

    public void putEntityManagerFactory(String persistenceUnitName, EntityManagerFactory emf) {
        if ((persistenceUnitName != null && !"".equals(persistenceUnitName)) && (emf != null)) {
            entityManagerFactory.remove(persistenceUnitName);
            entityManagerFactory.put(persistenceUnitName, emf);
            entityManagerThread.set((HashMap<String, EntityManagerFactory>) entityManagerFactory);
        }
    }

    public void removeEntityManagerFactory(String persistenceUnitName) {
        Map<String, EntityManagerFactory> em = entityManagerThread.get();
        if (em != null) {
            em.remove(persistenceUnitName);
            entityManagerThread.set((HashMap<String, EntityManagerFactory>) entityManagerFactory);
        }
    }

    public EntityManagerFactory getEntityManagerFactory(String persistenceUnitName) {
        Map<String, EntityManagerFactory> em = entityManagerThread.get();
        EntityManagerFactory factory = null;
        if (em != null && em.get(persistenceUnitName) != null) {
            factory = em.get(persistenceUnitName);
        }
        return factory;
    }
}