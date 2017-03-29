package org.apache.struts.example.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "STRUTS2_CRUD_EXAMPLE", name = "EMPLOYEE")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SQ_EMPLOYEE", sequenceName="STRUTS2_CRUD_EXAMPLE.SQ_EMPLOYEE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPLOYEE")
    @Column(name = "ID", nullable = false, precision = 5)
    private Integer employeeId;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "FIRST_NAME", nullable = false, length = 10)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 10)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public Employee() {
    }

    public Employee(Integer employeeId, String firstName, String lastName, Integer age, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
