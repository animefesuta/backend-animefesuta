package cn.baizhi958216.enums;

public enum ImageVisibilityEnum {

    PUBLIC(100, "PUBLIC"),
    PRIVATE(200, "PRIVATE");

    private final int code;
    private final String msg;

    ImageVisibilityEnum(int code, String msg) {
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
