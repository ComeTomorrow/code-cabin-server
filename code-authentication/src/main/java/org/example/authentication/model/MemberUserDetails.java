package org.example.authentication.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.common.core.constant.GlobalConstants;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * 会员用户
 *
 * @author ComeTomorrow
 * @since 2024/4/8
 */
@Data
@NoArgsConstructor
public class MemberUserDetails implements UserDetails, CredentialsContainer {
    /**
     * 会员ID
     */
    private Long id;

    /**
     * 会员用户名(openid/mobile)
     */
    private String username;

    /**
     * 会员密码
     */
    private String password;

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
     * @param memberAuthInfo 会员认证信息
     */
    public MemberUserDetails(MemberAuthDTO memberAuthInfo) {
        this.setId(memberAuthInfo.getId());
        this.setUsername(memberAuthInfo.getUsername());
        this.setPassword(memberAuthInfo.getPassword());
        this.setEnabled(GlobalConstants.STATUS_YES.equals(memberAuthInfo.getStatus()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_SET;
    }

    @Override
    public String getPassword() {
        return this.password;
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

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
