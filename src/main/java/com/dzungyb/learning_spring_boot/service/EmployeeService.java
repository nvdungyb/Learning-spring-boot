package com.dzungyb.learning_spring_boot.service;

import com.dzungyb.learning_spring_boot.model.Employee;
import com.dzungyb.learning_spring_boot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

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
