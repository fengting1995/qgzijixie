package com.rf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.rf.common.Constants;
import com.rf.dto.ReturnResult;
import com.rf.dto.ReturnResultUtils;
import com.rf.exception.UserException;
import com.rf.pojo.QgUser;
import com.rf.service.LocalUserService;
import com.rf.service.QgUserService;
import com.rf.utils.EmptyUtils;
import com.rf.utils.RedisUtils;
import com.rf.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class LocalUserServiceImpl implements LocalUserService {

   @Reference
   private QgUserService qgUserService;

   @Autowired
   private RedisUtils redisUtils;


    @Override
    public ReturnResult validateToken(String Phone, String password) throws Exception {
        QgUser qgUser = null;

        ReturnResult returnResult = null;
        qgUser = qgUserService.getQgUserByPhoneAndPwd(Phone, password);
        if(qgUser!=null){
            String oldToken = redisUtils.getStr(qgUser.getId());
            if (EmptyUtils.isNotEmpty(oldToken)){
                redisUtils.delStr(qgUser.getId());
            }
            String token = Constants.tokenPrefix+ TokenUtils.createToken(qgUser.getId(),qgUser.getPhone());
            redisUtils.setStr(token, JSONObject.toJSONString(qgUser),Constants.LOGIN_EXPIRE);
            //还有存token对应用户id，将来客户端发过来token时才能找到这个token是对应哪个用户，但是为什么要转成json字符串是个问题
            redisUtils.setStr(qgUser.getId(),token,Constants.LOGIN_EXPIRE);
            //这边常量为30L，即长整型，在redisutil中注明了该参数为时间，且单位为分钟，所以直接用常数。
            Map<String,String>result = new HashMap<String, String>();
            result.put("token",token);
            returnResult =  ReturnResultUtils.returnSuccess(result);
            //这边直接以map形式返回数据
        }else{
            returnResult = ReturnResultUtils.returnFail(UserException.USER_PASSWORD_ERROR.getCode(),UserException.USER_PASSWORD_ERROR.getMessage());
            //在consumer模块中定义了该模块出现异常时用的信息提示枚举类，这边就使用了，方式和之前的一样
        }
        return returnResult;
    }

    @Override
    public ReturnResult removeToken(String token) throws Exception{
        String qgUserJson = redisUtils.getStr(token);
        QgUser qgUser = JSONObject.parseObject(qgUserJson,QgUser.class);
        redisUtils.delStr(token);
        redisUtils.delStr(qgUser.getId());
        return ReturnResultUtils.returnSuccess();
    }


}
