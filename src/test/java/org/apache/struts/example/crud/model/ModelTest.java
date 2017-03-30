package org.apache.struts.example.crud.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class ModelTest {

    @Test
    public void test() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("struts2-crud-example");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();

            Department dep = new Department();
            dep.setName("Accounting");
            manager.persist(dep);

            Employee emp = new Employee();
            emp.setFirstName("Paulo");
            emp.setLastName("Ribeiro");
            emp.setAge(42);
            emp.setDepartment(dep);
            manager.persist(emp);
           
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

}
