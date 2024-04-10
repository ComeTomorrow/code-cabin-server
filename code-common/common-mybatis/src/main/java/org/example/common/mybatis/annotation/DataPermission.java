package org.example.common.mybatis.annotation;

import java.lang.annotation.*;

/**
 * MP数据权限注解
 *
 * @author ComeTomorrow
 * @since 2024/4/10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataPermission {

    /**
     * 数据权限 {@link com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor}
     */
    /* 数据权限注解，暂未开放，敬请期待 */

}

