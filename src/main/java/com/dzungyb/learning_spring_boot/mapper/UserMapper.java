package com.dzungyb.learning_spring_boot.mapper;

import com.dzungyb.learning_spring_boot.dto.request.UserCreationRequest;
import com.dzungyb.learning_spring_boot.dto.request.UserUpdateRequest;
import com.dzungyb.learning_spring_boot.dto.response.UserResponse;
import com.dzungyb.learning_spring_boot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    //    @Mapping(target = "id", ignore = true)
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

/*
 : Sử dụng _ nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE để bỏ qua các giá trị null trong quá trình mapping.
 */