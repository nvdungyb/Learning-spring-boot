package com.dzungyb.learning_spring_boot.repository;

import com.dzungyb.learning_spring_boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
