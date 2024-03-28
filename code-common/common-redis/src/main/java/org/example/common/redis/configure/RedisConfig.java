package org.example.common.redis.configure;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis缓存配置
 * 
 * @author ComeTomorrow
 * @Since 2024/3/28
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfig
{
    /**
     * 自定义 RedisTemplate
     * 修改 Redis 序列化方式，默认 JdkSerializationRedisSerializer
     * <p></p>
     * spring-data-redis默认使用的序列化方式为JdkSerializationRedisSerializer，
     * 当这个redis只在一个project中使用时，这是没问题的。但是有时候，我们的Redis会在
     * 一个项目的多个project中共用，这样如果同一个可以缓存的对象在不同的project中要
     * 使用两个不同的key来分别缓存，既麻烦，又浪费。好在spring-data-redis给我们提供
     * 了其它几种序列化方式，其中Json序列化提供了三种：JacksonJsonRedisSerializer、
     * Jackson2JsonRedisSerializer和GenericJackson2JsonRedisSerializer。
     * 第一个已经加上了@Deprecated注解，不建议使用。在RedisSerializer接口中调用
     * json()，事实上用的就是GenericJackson2JsonRedisSerializer的序列化方式。
     *
     * @param redisConnectionFactory {@link RedisConnectionFactory}
     * @return {@link RedisTemplate}
     */
    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());

        // Hash的key也采用StringRedisSerializer的序列化方式
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());

        // 往后可以再去扩展其他redis类型的序列化形式
        //......

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 自定义 RedisCacheManager
     * <p></p>
     * 给此缓存管理器设置好自定义的缓存配置器
     *
     * @param redisConnectionFactory {@link RedisConnectionFactory}
     * @param cacheProperties {@link CacheProperties}
     * @return {@link RedisCacheManager}
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, CacheProperties cacheProperties){
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration(cacheProperties))
                .build();
    }

    /**
     * 自定义 RedisCacheConfiguration
     * <p></p>
     * 可根据配置文件转换成Bean添加到缓存配置器中，实现定制配置，如果某些配置属性
     * 未在配置文件中定义，则可以在此实现自定义默认配置。
     *
     * @param cacheProperties {@link CacheProperties}
     * @return {@link RedisCacheConfiguration}
     */
    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        config = config.computePrefixWith(name -> name + ":");//覆盖默认key双冒号  CacheKeyPrefix#prefixed
        return config;
    }
}
