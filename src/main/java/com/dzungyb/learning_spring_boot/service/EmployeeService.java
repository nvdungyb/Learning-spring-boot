package com.dzungyb.learning_spring_boot.service;

import com.dzungyb.learning_spring_boot.entity.Employee;
import com.dzungyb.learning_spring_boot.entity.User;
import com.dzungyb.learning_spring_boot.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public List<Employee> listAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public void saveEmployee(Employee emp) {
        employeeRepository.save(emp);
    }

    public void updateEmployee(Employee emp) {
        employeeRepository.updateEmployee(emp.getFirstName(), emp.getLastName(), emp.getEmail());
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    public boolean checkEmailUnique(Employee employee) {
        Employee emp = employeeRepository.getEmployeeByEmail(employee.getEmail());
        if (emp != null)
            return false;
        return true;
    }

}
