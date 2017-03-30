package org.apache.struts.example.crud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public abstract class JpaSupport<T> {
    EntityManagerFactory factory = EntityManagerSession.getInstance().getEntityManagerFactory("struts2-crud-example");
    EntityManager manager;

    @SuppressWarnings("rawtypes")
    private Class clazz;

    @SuppressWarnings("rawtypes")
    public JpaSupport(Class clazz) {
        this.manager = factory.createEntityManager();
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        String entity = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
        Query q = manager.createQuery("select t from " + entity + " t");
        return q.getResultList();
    }
}