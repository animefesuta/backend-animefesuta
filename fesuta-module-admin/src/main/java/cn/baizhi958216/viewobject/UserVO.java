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
     * 用户社区唯一标识
     */
    private String uid;
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
     * 用户个性签名
     */
    private String instruction;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户个人中心背景
     */
    private String BackgroundImage;
}
