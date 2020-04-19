package com.fd.useradmin.config;

import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;


@Configuration
public class FeignRetryConfig {

    public Retryer myRetryer(){
        return new Retryer.Default(100,SECONDS.toMillis(1),3);
    }



}
