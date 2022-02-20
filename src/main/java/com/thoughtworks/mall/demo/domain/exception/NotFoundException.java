package com.thoughtworks.mall.demo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private ExceptionCode code;

    private String message;

    public NotFoundException(ExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
