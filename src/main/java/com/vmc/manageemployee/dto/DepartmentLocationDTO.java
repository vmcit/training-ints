package com.vmc.manageemployee.dto.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class tạo ra de test thu vs JDBC
 */
@Setter
@Getter
@NoArgsConstructor
public class DepartmentLocationDTO {

    private int location_id ;
    private String adress;
    private String city;
    private String country;

    public DepartmentLocationDTO(int location_id, String adress, String city, String country) {
        this.location_id = location_id;
        this.adress = adress;
        this.city = city;
        this.country = country;
    }
}
