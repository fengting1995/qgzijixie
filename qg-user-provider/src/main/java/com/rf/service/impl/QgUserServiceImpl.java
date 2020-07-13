package com.rf.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rf.mapper.QgUserMapper;
import com.rf.pojo.QgUser;
import com.rf.service.QgUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Component
@Service(interfaceClass = QgUserService.class)
public class QgUserServiceImpl implements QgUserService {

    @Resource
    private QgUserMapper qgUserMapper;

    @Override
    public QgUser getQgUserById(String id) throws Exception {
        return qgUserMapper.getQgUserById(id);
    }

    @Override
    public QgUser getQgUserByPhoneAndPwd(String phone, String password) throws Exception {
        Map<String,String>param = new HashMap<String, String>();
        param.put("phone",phone);
        param.put("password",password);
        return qgUserMapper.getQgUserByPhoneAndPwd(param);
    }

}
