package cn.baizhi958216.viewobject;

import cn.baizhi958216.enums.UserTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseVO {
    private String id;
    /**
     * 平台的类型
     * 枚举 {@link UserTypeEnum}
     */
    private Integer type;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户数据
     */
    private String rawUserInfo;
    /**
     * 用户密码
     */
    private String password;
}
