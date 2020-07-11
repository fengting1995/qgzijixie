package com.rf.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name="stringRedisTemplate")
    ValueOperations<String,String>valOpsStr;

    @Resource(name="redisTemplate")
    ValueOperations<Object,Object>valOpsObj;


    //设置str
    public void setStr(String key,String val){
        valOpsStr.set(key,val);
    }

    public void setStr(String key,String val,Long expire){
        valOpsStr.set(key,val,expire, TimeUnit.MINUTES);
    }

    public String getStr(String key){
        return valOpsStr.get(key);
    }

    public void  delStr(String key){
        stringRedisTemplate.delete(key);
    }

    //设置OBJ

    public void setObj(Object key, Object obj){
        valOpsObj.set(key,obj);
    }

    public void setObj(Object key,Object obj,Long expire){
        valOpsObj.set(key,obj,expire,TimeUnit.MINUTES);
    }

    public  Object getObj(Object key){
        return valOpsObj.get(key);
    }

    public void delObj(Object key){
        redisTemplate.delete(key);
    }



}
