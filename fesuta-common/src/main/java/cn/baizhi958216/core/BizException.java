package cn.baizhi958216.core;

public class BizException extends Exception {
    private Integer code;

    public BizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
