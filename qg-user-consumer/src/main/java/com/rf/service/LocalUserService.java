package com.rf.service;

import com.rf.dto.ReturnResult;
import com.rf.pojo.QgUser;

public interface LocalUserService {

    public QgUser testXiangmuDajianDemo()throws Exception;

    public ReturnResult validateToken(String Phone,String password)throws Exception;


}
