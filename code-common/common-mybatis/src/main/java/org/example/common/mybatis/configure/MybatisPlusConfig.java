package org.example.common.mybatis.configure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.example.common.mybatis.handler.IntegerArrayJsonTypeHandler;
import org.example.common.mybatis.handler.LongArrayJsonTypeHandler;
import org.example.common.mybatis.handler.IMetaObjectHandler;
import org.example.common.mybatis.handler.StringArrayJsonTypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus 配置类
 *
 * @author ComeTomorrow
 * @since 2024/4/9
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页插件和数据权限插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //数据权限
//        interceptor.addInnerInterceptor(new DataPermissionInterceptor(new IDataPermissionHandler()));
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MARIADB));

        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            // 全局注册自定义TypeHandler
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            typeHandlerRegistry.register(String[].class, JdbcType.OTHER, StringArrayJsonTypeHandler.class);
            typeHandlerRegistry.register(Long[].class, JdbcType.OTHER, LongArrayJsonTypeHandler.class);
            typeHandlerRegistry.register(Integer[].class, JdbcType.OTHER, IntegerArrayJsonTypeHandler.class);
        };
    }

    /**
     * 自动填充数据库创建人、创建时间、更新人、更新时间
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new IMetaObjectHandler());
        return globalConfig;
    }

}
