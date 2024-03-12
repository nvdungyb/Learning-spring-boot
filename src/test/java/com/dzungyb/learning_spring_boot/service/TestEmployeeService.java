package com.dzungyb.learning_spring_boot.service;

import com.dzungyb.learning_spring_boot.model.Employee;
import com.dzungyb.learning_spring_boot.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TestEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        Employee emp = new Employee("Kim", "Dung", "Dung2@gmail.com");
        employeeRepository.save(emp);
    }
}
