package org.apache.struts.example.crud.service;

import java.util.List;

import org.apache.struts.example.crud.model.Department;

public interface DepartmentService {

    public List<Department> getAllDepartments();

}