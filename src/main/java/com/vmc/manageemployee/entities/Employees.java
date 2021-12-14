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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthDate")
    private String birthDate;

    @OneToOne(mappedBy = "employees", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Department department;

    public Employees(int id, String fullName, String gender, String birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

}

