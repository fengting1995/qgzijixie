package com.rf.service;


import com.rf.pojo.QgUser;

public interface QgUserService {

    public QgUser getQgUserById(String id)throws Exception;

    public QgUser getQgUserByPhoneAndPwd(String phone,String password)throws Exception;


}
