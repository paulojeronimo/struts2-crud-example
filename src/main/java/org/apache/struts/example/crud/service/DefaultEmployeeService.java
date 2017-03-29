package org.apache.struts.example.crud.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.apache.struts.example.crud.dao.EmployeeDao;
import org.apache.struts.example.crud.dao.InMemoryEmployeeDao;
import org.apache.struts.example.crud.model.Employee;

public class DefaultEmployeeService implements EmployeeService {
    private static final Logger logger = Logger.getLogger(DefaultEmployeeService.class.getName());

    private EmployeeDao dao;

    public DefaultEmployeeService() {
        this.dao = new InMemoryEmployeeDao();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    @Override
    public void updateEmployee(Employee emp) {
        logger.info("updateEmployee called");
        dao.update(emp);
    }

    @Override
    public void deleteEmployee(Integer id) {
        logger.info("deleteEmployee called");
        dao.delete(id);
    }

    @Override
    public Employee getEmployee(Integer id) {
        logger.info("getEmployee called");
        return dao.getEmployee(id);
    }

    @Override
    public void insertEmployee(Employee emp) {
        logger.info("insertEmployee called");
        dao.insert(emp);
    }
}