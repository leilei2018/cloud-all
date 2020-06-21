package com.fd.swagger;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ClassUtils;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@Slf4j
@EnableEurekaServer
@MapperScan("com.fd.swagger.mapper")
@EnableFeignClients(basePackages = "com.fd.swagger.feign")
public class SwaggerApplication {
    static ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();

    static void test() throws IOException {
        Resource[] resources = RESOURCE_PATTERN_RESOLVER.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath("com.fd.swagger.model.entity") + "/**/*.class");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SwaggerApplication.class,args);
    }
}
