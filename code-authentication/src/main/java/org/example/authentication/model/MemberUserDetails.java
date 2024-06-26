package org.example.authentication.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cabin.ums.dto.MemberAuthDTO;
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
@Setter
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
     * 会员昵称
     */
    private String nickName;

    /**
     * 会员密码
     */
    private String password;

    /**
     * 会员状态
     */
    private Boolean enabled;

    /**
     * 帐户未过期
     */
    private Boolean accountNonExpired;

    /**
     * 帐户未锁定
     */
    private Boolean accountNonLocked;

    /**
     * 凭据未过期
     */
    private Boolean credentialsNonExpired;

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
        this.setNickName(memberAuthInfo.getNickName());
        this.setPassword(memberAuthInfo.getPassword());
        this.setEnabled(memberAuthInfo.getEnabled());
        this.setAccountNonExpired(memberAuthInfo.getAccountNonExpired());
        this.setAccountNonLocked(memberAuthInfo.getAccountNonLocked());
        this.setCredentialsNonExpired(memberAuthInfo.getCredentialsNonExpired());
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
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public String getNickName() {
        return this.nickName;
    }

    public Long getId() {
        return this.id;
    }
}
