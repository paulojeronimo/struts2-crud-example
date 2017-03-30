package org.apache.struts.example.crud.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.apache.struts.example.crud.annotations.PersistenceUnitConfiguration;
import org.apache.struts.example.crud.dao.DepartmentDao;
import org.apache.struts.example.crud.dao.JpaDepartmentDao;
import org.apache.struts.example.crud.interceptors.BaseInterceptor;
import org.apache.struts.example.crud.model.Department;

@Stateless(name = "DepartmentService", mappedName = "DepartmentService")
@EJB(name = "DepartmentService", beanInterface = DepartmentService.class)
//@Resource(name = "java:/Struts2CrudExampleDS", type = DataSource.class, authenticationType = AuthenticationType.CONTAINER)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Interceptors({ BaseInterceptor.class })
@PersistenceUnitConfiguration(persistenceUnitName = "struts2-crud-example", entityManagerField = "sigmeEntityManager")
public class JpaDepartmentService implements DepartmentService {
    public static final String _persistenceUnitName = "struts2-crud-example";
    
    @PersistenceUnit(name = _persistenceUnitName)
    public EntityManagerFactory factory;

    private DepartmentDao dao;

    public JpaDepartmentService() {
        this.dao = new JpaDepartmentDao();
    }

    public List<Department> getAllDepartments() {
        return dao.getAllDepartments();
    }

}