package com.dzungyb.learning_spring_boot.exception;

public class AppException extends RuntimeException {
    private ErrorCode errocode;

    public AppException(ErrorCode errocode) {
        super(errocode.getMessage());
        this.errocode = errocode;
    }

    public ErrorCode getErrocode() {
        return errocode;
    }

    public void setErrocode(ErrorCode errocode) {
        this.errocode = errocode;
    }
}
