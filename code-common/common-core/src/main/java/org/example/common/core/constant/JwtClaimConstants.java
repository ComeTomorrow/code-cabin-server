package org.example.common.core.constant;

/**
 * Jwt载荷常量
 *
 * @author ComeTomorrow
 * @since 2024/3/30
 */
public interface JwtClaimConstants {

    /**
     * 用户ID
     */
    String USER_ID = "userId";

    /**
     * 手机
     */
    String MOBILE = "mobile";

    /**
     * 会员昵称
     */
    String NICK_NAME = "nickName";

    /**
     * 启用
     */
    String ENABLED = "enabled";

    /**
     * 帐户未过期
     */
    String ACCOUNT_NON_EXPIRED = "accountNonExpired";

    /**
     * 帐户未锁定
     */
    String ACCOUNT_NON_LOCKED = "accountNonLocked";

    /**
     * 凭据未过期
     */
    String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";

    /**
     * 数据权限
     */
    String DATA_SCOPE = "dataScope";

    /**
     * 权限(角色Code)集合
     */
    String AUTHORITIES = "authorities";

    /**
     * 会员ID
     */
    String MEMBER_ID = "memberId";

    /**
     * JWT秘钥
     */
    String JWT_KEY = "cxf";
}
