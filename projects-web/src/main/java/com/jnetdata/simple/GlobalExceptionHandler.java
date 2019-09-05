package com.jnetdata.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thenicesys.web.JsonResult;

import java.util.stream.Collectors;

/**
 * Controller异常全局处理
 *
 * @author Administrator
 * @date 2018/9/17
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        log.error("GlobalExceptionHandler:handleMethodArgumentNotValidException>> " + e.getMessage(), e);
        return JsonResult.renderError(createFormatResultMessage(e.getBindingResult()));
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResult handleBindException(BindException e){

        log.error("GlobalExceptionHandler:handleBindException>> " + e.getMessage(), e);
        return JsonResult.renderError(e.getBindingResult());
    }

    private String createFormatResultMessage(BindingResult bindingResult) {
        String message = bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.toString()).collect(Collectors.joining("; "));
        return message;
    }

    // 其他各种异常

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e){

        log.error("GlobalExceptionHandler:handleException>> " + e.getMessage(), e);
        return JsonResult.renderError(e.getMessage());
    }

}
