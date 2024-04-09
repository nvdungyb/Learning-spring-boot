package com.dzungyb.learning_spring_boot.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserUpdateRequest {
    String userName;
    String password;
    String firstName;
    String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dob;

}

/* @Builder
: Để tạo ra các builder pattern tự động cho các lớp java.
_ Nhằm tạo ra một mẫu thiết kế giúp tạo ra đối tượng một cách linh hoạt và dễ đọc.
_ Giúp tránh được việc sử dụng nhiều constructor khác nhau.

 */
