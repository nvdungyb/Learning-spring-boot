package com.dzungyb.learning_spring_boot.service;

import com.dzungyb.learning_spring_boot.dto.request.UserCreationRequest;
import com.dzungyb.learning_spring_boot.dto.request.UserUpdateRequest;
import com.dzungyb.learning_spring_boot.dto.response.UserResponse;
import com.dzungyb.learning_spring_boot.entity.User;
import com.dzungyb.learning_spring_boot.exception.AppException;
import com.dzungyb.learning_spring_boot.exception.ErrorCode;
import com.dzungyb.learning_spring_boot.mapper.UserMapper;
import com.dzungyb.learning_spring_boot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
//        List<UserResponse> userResponses = new ArrayList<>();
//
//        for (User user : users) {
//            userResponses.add(userMapper.toUserResponse(user));
//        }

        List<UserResponse> userResponses = users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

        return userResponses;
    }

    public UserResponse getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        if (userRepository.existsById(userId) == false) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }
}

/* Lưu ý:
_ Ở tầng service khi trả về dữ liệu cho tầng controller thì nên trả về dữ liệu đã được xử lý, không nên trả về dữ liệu trực tiếp từ repository.
=> nên trả về dto khác vì ta có nhu cầu trả về object nhất định chứ không trả về toàn bộ entity.


 */