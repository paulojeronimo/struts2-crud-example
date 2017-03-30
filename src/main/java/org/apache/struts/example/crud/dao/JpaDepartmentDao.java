package org.apache.struts.example.crud.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.example.crud.model.Department;

public class JpaDepartmentDao extends JpaSupport<Department> implements DepartmentDao {

    private static List<Department> departments;
    private static Map<Integer, Department> departmentsMap;

    public JpaDepartmentDao() {
        super(Department.class);
    }

    public List<Department> getAllDepartments() {
        if (departments == null) {
            departments = list();
        }
        return departments;
    }

    public Map<Integer, Department> getDepartmentsMap() {
        if (departmentsMap == null) {
            departmentsMap = new HashMap<Integer, Department>();
            int i = 0;
            for (Department dep : getAllDepartments())
                departmentsMap.put(++i, dep);
        }
        return departmentsMap;
    }

}