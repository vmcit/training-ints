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

    private int locationId ;
    private String adress;
    private String city;
    private String country;

    public DepartmentLocation(int locationId, String adress, String city, String country) {
        this.locationId = locationId;
        this.adress = adress;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return "DepartmentLocation{" +
                "locationId='" + locationId + '\'' +
                ", adress='" + adress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
