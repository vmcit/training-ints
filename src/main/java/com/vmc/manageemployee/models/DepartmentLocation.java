package com.vmc.manageemployee.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class tạo ra de test thu vs JDBC
 */
@Setter
@Getter
@NoArgsConstructor
public class DepartmentLocation {

    private int location_id ;
    private String adress;
    private String city;
    private String country;

    public DepartmentLocation(int location_id, String adress, String city, String country) {
        this.location_id = location_id;
        this.adress = adress;
        this.city = city;
        this.country = country;
    }
}
