package com.thoughtworks.mall.demo.domain.exception;

import com.thoughtworks.mall.demo.application.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
@Order(ExceptionAdvice.BASE_ADVICE_ORDER)
public class ExceptionAdvice {

    public static final int BASE_ADVICE_ORDER = 200;


    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException exception) {
        log.error(exception.getMessage());
        return error(exception.getCode(), exception.getMessage());
    }

    private ErrorResponse error(ExceptionCode code, Object... args) {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .code(code)
                .message(StringUtils.join(args, "|"))
                .build();
        return new ErrorResponse(error);
    }

}
