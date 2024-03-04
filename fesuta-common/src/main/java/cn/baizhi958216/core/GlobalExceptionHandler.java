package cn.baizhi958216.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import cn.baizhi958216.enums.ResponseCodeEnum;
import cn.baizhi958216.viewobject.ResponseVO;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 全局异常处理
     * 
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseVO<Object> exceptionHandler(Exception e) {
        // 处理业务异常
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            if (bizException.getCode() == null) {
                bizException.setCode(ResponseCodeEnum.BAD_REQUEST.getCode());
            }
            return ResponseVO.failure(bizException.getCode(), bizException.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            // 参数检验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            Map<String, String> map = new HashMap<>();
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            result.getFieldErrors().forEach(item -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            log.error("数据校验出现错误：", e);
            return ResponseVO.failure(ResponseCodeEnum.BAD_REQUEST, map);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error("请求方法错误：", e);
            return ResponseVO.failure(ResponseCodeEnum.BAD_REQUEST.getCode(), "请求方法不正确");
        } else if (e instanceof MissingServletRequestParameterException) {
            log.error("请求参数缺失：", e);
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            return ResponseVO.failure(ResponseCodeEnum.BAD_REQUEST.getCode(), "请求参数缺少: " + ex.getParameterName());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("请求参数类型错误：", e);
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            return ResponseVO.failure(ResponseCodeEnum.BAD_REQUEST.getCode(), "请求参数类型不正确：" + ex.getName());
        } else if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            log.error("请求地址不存在：", e);
            return ResponseVO.failure(ResponseCodeEnum.NOT_FOUND, ex.getRequestURL());
        } else if (e instanceof UsernameNotFoundException) {
            UsernameNotFoundException ex = (UsernameNotFoundException) e;
            log.error("用户不存在：", e);
            return ResponseVO.failure(ResponseCodeEnum.UNAUTHORIZED, ex.getMessage());
        } else if (e instanceof ExpiredJwtException) {
            ExpiredJwtException ex = (ExpiredJwtException) e;
            log.error("token过期：", ex.getMessage());
            return ResponseVO.failure(ResponseCodeEnum.UNAUTHORIZED, "token过期，请重新登录");
        } else {
            // 如果是系统的异常，比如空指针这些异常
            log.error("【系统异常】", e);
            return ResponseVO.failure(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getCode(),
                    ResponseCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}
