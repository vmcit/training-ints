package com.vmc.manageemployee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Class tạo ra de test thu vs JDBC
 */
@Setter
@Getter
@NoArgsConstructor
public class Employee {
    private int Id;
    private String fullName;
    private String gender;
    private String birthDate;
    private int DepartmentId;

    public Employee(int id, String fullName, String gender, String birthDate, int departmentId) {
        Id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        DepartmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", DepartmentId=" + DepartmentId +
                '}';
    }
}
