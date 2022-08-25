package com.rf.service;

import com.rf.dto.ReturnResult;
import com.rf.pojo.QgUser;

public interface LocalUserService {


    public ReturnResult validateToken(String Phone,String password)throws Exception;

    public ReturnResult removeToken(String token)throws Exception;

}
