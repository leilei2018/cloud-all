package com.fd.swagger.config;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {
    static ThreadLocal<Map> map = new ThreadLocal<Map>(){
        @Override
        protected Map initialValue() {
            return new HashMap();
        }
    };

    public static void put(String key,Object value){
        map.get().put(key,value);
    }
    public static Map get(){
        return map.get();
    }
    public static void remove(){
        map.remove();
    }
}
