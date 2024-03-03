package cn.baizhi958216.enums;

import cn.baizhi958216.core.IntArray;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserTypeEnum implements IntArray {
    /**
     * ADMIN: 管理员
     */
    ADMIN(10, "ADMIN"),

    /**
     * 商家
     */
    BUSINESS(20, "BUSINESS"),

    /**
     * COSER: COSER
     */
    COSER(20, "COSER"),

    /**
     * USER: 注册用户
     */
    USER(40, "USER");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserTypeEnum::getType).toArray();

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 类型的标识
     */
    private final String source;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
