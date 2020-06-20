package com.rf.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rf.mapper.QgUserMapper;
import com.rf.pojo.QgUser;
import com.rf.service.QgUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Service(interfaceClass = QgUserService.class)
public class QgUserServiceImpl implements QgUserService {

    @Resource
    private QgUserMapper qgUserMapper;

    @Override
    public QgUser getQgUserById(String id) throws Exception {
        return qgUserMapper.getQgUserById(id);
    }


}
