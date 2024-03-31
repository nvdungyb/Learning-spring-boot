package com.dzungyb.learning_spring_boot.service;

import com.dzungyb.learning_spring_boot.dto.request.UserCreationRequest;
import com.dzungyb.learning_spring_boot.dto.request.UserUpdateRequest;
import com.dzungyb.learning_spring_boot.exception.AppException;
import com.dzungyb.learning_spring_boot.exception.ErrorCode;
import com.dzungyb.learning_spring_boot.mapper.UserMapper;
import com.dzungyb.learning_spring_boot.model.User;
import com.dzungyb.learning_spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);
        userMapper.updateUser(user, request);

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}

/* Lưu ý:
_ Ở tầng service khi trả về dữ liệu cho tầng controller thì nên trả về dữ liệu đã được xử lý, không nên trả về dữ liệu trực tiếp từ repository.
=> nên trả về dto khác vì ta có nhu cầu trả về object nhất định chứ không trả về toàn bộ entity.
 */