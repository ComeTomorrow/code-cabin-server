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
     * 会员昵称
     */
    String NICKNAME = "nickName";

    /**
     * 部门ID
     */
    String DEPT_ID = "deptId";

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
