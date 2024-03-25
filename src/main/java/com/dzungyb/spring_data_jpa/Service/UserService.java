package com.dzungyb.spring_data_jpa.Service;

import com.dzungyb.spring_data_jpa.model.User;
import com.dzungyb.spring_data_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).get();
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

}
