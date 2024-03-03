package cn.baizhi958216.dataobject;

import cn.baizhi958216.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "fusta_users")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO extends BaseDO {
    @Builder.Default
    @Id
    private String id = UUID.randomUUID().toString();
    /**
     * 平台的类型
     * 枚举 {@link UserTypeEnum}
     */
    private Integer type;
    /**
     * 用户昵称
     */
    @NonNull
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
    @NonNull
    private String password;
}
