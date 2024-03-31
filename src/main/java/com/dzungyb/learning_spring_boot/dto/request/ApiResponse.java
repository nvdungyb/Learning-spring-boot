package com.dzungyb.learning_spring_boot.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)      // Bỏ qua các trường null khi trả về response.
public class ApiResponse<T> {
    private int code = 1000;
    private String message;
    private T result;

}

// Đây là response API chuẩn.