package com.souyunku.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Component
public class Commons {

    /**
     * 读取限流脚本
     *
     * @return
     * @throws IOException 
     */
    @Bean
    public DefaultRedisScript<Number> redisluaScript() throws IOException {
        DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
        ClassPathResource classPathResource = new ClassPathResource("rateLimit.lua");
        System.out.println("lua path :"+classPathResource.getPath());
        ResourceScriptSource resourceScriptSource = new ResourceScriptSource(classPathResource);
        redisScript.setScriptSource(resourceScriptSource);
        redisScript.setResultType(Number.class);
        return redisScript;
    }

    /**
     * RedisTemplate
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<String, Serializable>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
