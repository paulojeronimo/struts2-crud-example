package org.apache.struts.example.crud.service;

import java.util.List;

import org.apache.struts.example.crud.dao.DepartmentDao;
import org.apache.struts.example.crud.dao.InMemoryDepartmentDao;
import org.apache.struts.example.crud.model.Department;

public class DefaultDepartmentService implements DepartmentService {

    private DepartmentDao dao;

    public DefaultDepartmentService() {
        this.dao = new InMemoryDepartmentDao();
    }

    public List<Department> getAllDepartments() {
        return dao.getAllDepartments();
    }

}