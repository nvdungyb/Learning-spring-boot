package com.dzungyb.learning_spring_boot.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    @NotNull
    private String userName;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dob;

}

// @Email, @NotBlank, @NotEmpty, @NotNull, @Null, @Pattern, @Size, @Valid, @Validated
// Tự tạo annotation cho riêng mình.

/* Lombok
: Mặc định sẽ generate các phương thức getter and setter cho các field private.
_ Dùng @Data -> tự động generate getter, setter, constructor, toString, equals, hashCode.

_

 */