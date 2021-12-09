package com.vmc.manageemployee.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EMPLOYEES",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "Id" }) })
public class Employee {
    
    private int Id;
    private String fullName;
    private String gender;
    private String birthDate;
}
