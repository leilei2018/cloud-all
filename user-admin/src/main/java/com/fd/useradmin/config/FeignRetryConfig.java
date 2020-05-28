package com.fd.useradmin.config;

import feign.Logger;
import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;


@Configuration
public class FeignRetryConfig {

    public Retryer myRetryer(){
        return new Retryer.Default(100,SECONDS.toMillis(1),3);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
