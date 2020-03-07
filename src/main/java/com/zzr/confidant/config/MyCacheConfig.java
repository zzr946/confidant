package com.zzr.confidant.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description:指定自己的MyKeyGenerator
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/7
 */
@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName() + "[" + Arrays.asList(objects).toString() + "]";
            }
        };
    }
}
