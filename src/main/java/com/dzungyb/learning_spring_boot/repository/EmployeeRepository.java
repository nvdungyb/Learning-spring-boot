package com.dzungyb.learning_spring_boot.repository;

import com.dzungyb.learning_spring_boot.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT emp FROM Employee emp WHERE emp.email = :email")
    public Employee getEmployeeByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Employee emp SET emp.firstName = :firstName, emp.lastName = :lastName WHERE emp.email = :email")
    public void updateEmployee(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email);
}
