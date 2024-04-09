package com.dzungyb.learning_spring_boot.exception;

public enum ErrorCode {
    USER_EXISTED(1002, "User existed"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters long"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters long"),
    INVALID_ARGUMENT(1001, "Argument not valid Exception"),
    USER_NOT_FOUND(1005, "User not found"),
    WRONG_USERNAME_PASSWORD(1006, "Username or password is incorrect"),
    UNAUTHENTICATED(1007, "Unauthenticated"),
    ;

    private int code;

    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
