package com.vmc.manageemployee.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT_LOCATION")
@Setter
@Getter
@NoArgsConstructor
public class DepartmentLocation {
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int locationId;

    @Column(name = "adress")
    private String adress;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy="departmentLocation")
    private Set<Department> department;

    public DepartmentLocation(int locationId, String adress, String city, String country) {
        this.locationId = locationId;
        this.adress = adress;
        this.city = city;
        this.country = country;
    }
}
