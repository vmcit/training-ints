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
public class EmployeeDTO {
    private int Id;
    private String full_name;
    private String gender;
    private String birth_date;
    private int department_id;

    public EmployeeDTO(int id, String full_name, String gender, String birth_date, int department_id) {
        Id = id;
        this.full_name = full_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.department_id = department_id;
    }
}
