package com.sayantan.employeebatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class EmployeeBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeBatchApplication.class, args);
    }

}
