package org.example.cabin.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员信息
 *
 * @author ComeTomorrow
 * @since 2024/4/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAuthDTO {

    /**
     * 会员ID
     */
    private Long id;

    /**
     * 会员名(openId、mobile)
     */
    private String username;

    /**
     * 会员昵称
     */
    private String nickName;

    /**
     * 系统生成的唯一编码
     */
    private String muCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态(true:正常；false：禁用)
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
}
