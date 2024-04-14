package com.dzungyb.learning_spring_boot.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String userName;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<String> roles;
}
