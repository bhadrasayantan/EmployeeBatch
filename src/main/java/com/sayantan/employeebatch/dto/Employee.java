package com.sayantan.employeebatch.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String nationality;

    public Employee(String firstName, String lastName, LocalDate dob, String gender, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.nationality = nationality;
    }
}
