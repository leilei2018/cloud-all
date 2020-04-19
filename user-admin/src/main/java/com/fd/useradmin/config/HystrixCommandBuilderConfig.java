package com.fd.useradmin.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Target;
import feign.hystrix.SetterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class HystrixCommandBuilderConfig {
    @Bean
    public SetterFactory mySetterFactory(){
        return new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                String groupKey = target.name();
                String commandKey = Feign.configKey(target.type(), method);
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                        .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500))
                        ;
            }
        };
    }

}
