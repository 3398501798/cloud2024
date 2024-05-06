package com.nuc.cloud.exp;

import com.nuc.cloud.resp.ResultCodeEnum;
import com.nuc.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e){
        System.out.println("#####come in GlobalExceptionHandler");
        log.error("全局异常信息：{}",e.getMessage(),e);

        return ResultData.fail(ResultCodeEnum.RC500.getCode(), e.getMessage());
    }
}
