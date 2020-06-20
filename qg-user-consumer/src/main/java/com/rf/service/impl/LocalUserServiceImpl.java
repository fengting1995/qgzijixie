package com.rf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rf.pojo.QgUser;
import com.rf.service.LocalUserService;
import com.rf.service.QgUserService;
import org.springframework.stereotype.Component;

@Component
public class LocalUserServiceImpl implements LocalUserService {

   @Reference
   private QgUserService qgUserService;

    @Override
    public QgUser testXiangmuDajianDemo() throws Exception {
        String id ="2";
        return qgUserService.getQgUserById(id);
    }
}
