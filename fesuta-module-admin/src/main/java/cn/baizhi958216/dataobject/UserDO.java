package cn.baizhi958216.dataobject;

import cn.baizhi958216.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "fusta_users")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO extends BaseDO implements UserDetails {
    @Builder.Default
    @Id
    private String id = UUID.randomUUID().toString();

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
    @NonNull
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
    @NonNull
    private String password;
    /**
     * 用户个人中心背景
     */
    private String backgroundImage;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
