package com.vmc.manageemployee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class tạo ra de test thu vs JDBC
 */
@Setter
@Getter
@NoArgsConstructor
public class DepartmentDTO {

    private int department_id;
    private String department_name ;
    private String duty;
    private int location_id ;

    public DepartmentDTO(int department_id, String department_name, String duty, int location_id) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.duty = duty;
        this.location_id = location_id;
    }
}
