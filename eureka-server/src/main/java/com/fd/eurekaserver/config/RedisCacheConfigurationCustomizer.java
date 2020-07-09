package com.fd.eurekaserver.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.Map;
@Configuration
public class RedisCacheConfigurationCustomizer {

    @Bean
    public RedisCacheConfiguration rccg(){
        RedisCacheConfiguration rg =  RedisCacheConfiguration.defaultCacheConfig();
        rg = rg.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                //RedisSerializer.json()  用jkson通用的序列化，在redis数据和对象属性字段不相等时候，就会报错，但是用fastjson通用版就不会
                //new GenericFastJsonRedisSerializer()
                new KyroRedisSerializer()
        ));
        rg =  rg.entryTtl(Duration.ofMinutes(5));
        //默认key的前缀是，缓存cache::,  即cache::${key}

        //rg = rg.prefixKeysWith("qaz");
        //如果设置了prefix,则变成  qaz${key} ,没什么必要而且不太好，默认使用对应的cacheName就好
        return rg;
    }

}
