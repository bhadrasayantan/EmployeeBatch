package com.sayantan.employeebatch.batch.processor;

import com.sayantan.employeebatch.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeProcessor.class);
    @Override
    public Employee process(Employee employee) throws Exception {
        LOGGER.info("Transforming Employee Values from existing case to upper case");
        employee.setFirstName(employee.getFirstName().toUpperCase());
        employee.setLastName(employee.getLastName().toUpperCase());
        employee.setGender(employee.getGender().toUpperCase());
        employee.setNationality(employee.getNationality().toUpperCase());
        return employee;
    }
}
