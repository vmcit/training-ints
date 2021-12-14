package com.vmc.manageemployee.entities;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
public class Employees {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthDate")
    private String birthDate;

    @ManyToOne
    @JoinColumn(name="departmentId", nullable=false)
    private Department department;

    public Employees(int id, String fullName, String gender, String birthDate, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.department = department;
    }
}

