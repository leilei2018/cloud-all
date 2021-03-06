package com.fd.eurekaserver.service.impl;

import com.fd.eurekaserver.service.ValidateService;
import com.fd.eurekaserver.vo.LogVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.cache.annotation.CacheKey;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "books")
public class ValidateServiceImpl implements ValidateService {


    @Override
    public boolean vad2(LogVo logVo) {
        return false;
    }

    @Override
    public boolean vad3(LogVo logVo) {
        return false;
    }

    @Override
    public boolean vad4(String uuid) {
        return false;
    }




    @Override
    @Cacheable( key="#uuid.name") //spel expression
    /**
     * 如果是基本类型，则直接#key   --> String aa  #aa
     * 如果是对象，则#key.pros         Log vo     #vo.name
     */
    public LogVo find( LogVo uuid) {
        System.out.println("查询key:"+uuid.getName());
        LogVo tt = new LogVo();
        tt.setName("heapn");
        tt.setDate("2020-06-02");
        tt.setAge("33");
        return tt;
    }

    @Override
    @CacheEvict(allEntries = true,beforeInvocation = true)
    public void deleteAllcache() {

    }
}
