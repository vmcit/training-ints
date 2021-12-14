package com.vmc.manageemployee.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Department")
@Setter
@Getter
@NoArgsConstructor
public class Department {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int departmentId;

    @Column(name = "departmentName")
    private String departmentName ;

    @Column(name = "duty")
    private String duty;

    @OneToMany(mappedBy="department")
    private Set<Employees> employees;

    @ManyToOne
    @JoinColumn(name="locationId", nullable=false)
    private DepartmentLocation departmentLocation;


    public Department(int departmentId, String departmentName, String duty) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.duty = duty;
    }
}
