package org.apache.struts.example.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(schema = "STRUTS2_CRUD_EXAMPLE", name = "DEPARTMENT", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SQ_DEPARTMENT", sequenceName="STRUTS2_CRUD_EXAMPLE.SQ_DEPARTMENT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEPARTMENT")
    @Column(name = "ID", nullable = false, precision = 5)
    private Integer departmentId;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    public Department() {
    }

    public Department(Integer departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
