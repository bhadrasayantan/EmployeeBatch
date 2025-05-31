package com.sayantan.employeebatch.batch.mapper;

import com.sayantan.employeebatch.dto.Employee;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeMapper implements FieldSetMapper<Employee> {
    @Override
    public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
        Employee employee = new Employee();
        employee.setFirstName(fieldSet.readString(0));
        employee.setLastName(fieldSet.readString(1));
        DateTimeFormatter sdf =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        employee.setDob(LocalDate.parse(fieldSet.readString(2),sdf));
        employee.setGender(fieldSet.readString(3));
        employee.setNationality(fieldSet.readString(4));
        return employee;
    }
}
