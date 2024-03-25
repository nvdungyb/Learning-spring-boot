package com.dzungyb.spring_data_jpa.repository;

import com.dzungyb.spring_data_jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// Chỗ này không cho @Repository đc hay không?
public interface UserRepository extends JpaRepository<User, Long> {
}
