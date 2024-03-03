package cn.baizhi958216.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.baizhi958216.enums.ResponseCodeEnum;
import cn.baizhi958216.viewobject.ResponseVO;
import jakarta.annotation.Resource;

@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    public ResponseResultBodyAdvice responseResultBodyAdvice() {
        return new ResponseResultBodyAdvice();
    }

    @Resource
    private ObjectMapper objectMapper;

    @NonNull
    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(@NonNull MethodParameter returnType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE)
                || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {
        // 如果返回类型是string，那么springmvc是直接返回的，此时需要手动转化为json
        // 当body都为null时，下面的if判断条件都不满足，如果接口返回类似为String，
        // 会报错 cannot be cast to java.lang.String
        Method method = returnType.getMethod();
        Class<?> returnClass;
        if (method != null) {
            returnClass = method.getReturnType();
            if (body instanceof String || Objects.equals(returnClass, String.class)) {
                String value;
                try {
                    value = objectMapper.writeValueAsString(ResponseVO.success(body));
                    return value;
                } catch (JsonProcessingException e) {
                    return ResponseVO.failure(ResponseCodeEnum.INTERNAL_SERVER_ERROR, null);
                }
            }
            // 防止重复包裹的问题出现
            if (body instanceof ResponseVO) {
                return body;
            }
            return ResponseVO.success(body);
        } else {
            return ResponseVO.failure(ResponseCodeEnum.METHOD_NOT_ALLOWED, null);
        }

    }

}
