package com.sayantan.employeebatch;

import com.sayantan.employeebatch.batch.config.EmployeeBatchConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeBatchApplicationTests {
    @Autowired
    private EmployeeBatchConfiguration employeeBatchConfiguration;
    @Test
    void contextLoads() {
        Assertions.assertNotNull(employeeBatchConfiguration);
    }

}
