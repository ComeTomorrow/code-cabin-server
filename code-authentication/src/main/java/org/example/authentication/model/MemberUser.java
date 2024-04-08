package org.example.authentication.model;

import lombok.Data;
import org.example.authentication.lishi.MemberAuthDTO;
import org.example.common.core.constant.GlobalConstants;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * 会员用户
 *
 * @author ComeTomorrow
 * @since 2024/4/8
 */
@Data
public class MemberUser implements MemberUserDetails{
    /**
     * 会员ID
     */
    private Long id;

    /**
     * 会员用户名(openid/mobile)
     */
    private String username;

    /**
     * 会员状态
     */
    private Boolean enabled;

    /**
     * 扩展字段：认证身份标识，枚举值如下：
     *
     */
    private String authenticationIdentity;

    /**
     * 会员信息构造
     *
     * @param memAuthInfo 会员认证信息
     */
    public MemberUser(MemberAuthDTO memAuthInfo) {
        this.setId(memAuthInfo.getId());
        this.setUsername(memAuthInfo.getUsername());
        this.setEnabled(GlobalConstants.STATUS_YES.equals(memAuthInfo.getStatus()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_SET;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.enabled;
    }
}
