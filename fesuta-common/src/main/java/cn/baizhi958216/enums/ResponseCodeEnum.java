package cn.baizhi958216.enums;

public enum ResponseCodeEnum {

    SUCCESS(200, "success"),
    FAILURE(201, "failure"),
    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),
    METHOD_NOT_ALLOWED(405, "method not allowed"),
    INTERNAL_SERVER_ERROR(500, "internal server error"),
    PARAM_ERROR(202, "param error");

    private final int code;
    private final String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }
}
