package com.vmc.manageemployee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class tạo ra de test thu vs JDBC
 */
@Setter
@Getter
@NoArgsConstructor
public class Department {

    private int departmentId;
    private String departmentName ;
    private String duty;
    private int locationId ;

    public Department(int departmentId, String departmentName, String duty, int locationId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.duty = duty;
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", duty='" + duty + '\'' +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}
