package com.vmc.manageemployee.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Department")
@Setter
@Getter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId")
    private int departmentId;

    @Column(name = "departmentName")
    private String departmentName ;

    @Column(name = "duty")
    private String duty;

    @OneToOne
    @MapsId
    @JoinColumn(name = "DepartmentId")
    private Employees employees;

    @ManyToOne
    @JoinColumn(name="locationId", nullable=false)
    private DepartmentLocation departmentLocation;


    public Department(int departmentId, String departmentName, String duty) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.duty = duty;
    }
}
