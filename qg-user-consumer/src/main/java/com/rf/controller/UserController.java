package com.rf.controller;

import com.rf.dto.ReturnResult;
import com.rf.pojo.QgUser;
import com.rf.service.LocalUserService;
import com.rf.service.QgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {

        @Autowired
        private LocalUserService localUserService;

        @RequestMapping("/testXiangmuDajianDemo")
        public QgUser testXiangmuDajianDemo()throws Exception{
            return localUserService.testXiangmuDajianDemo();
        }


        @RequestMapping("/doLogin")
        public ReturnResult doLogin(String phone,String password)throws Exception{
                return localUserService.validateToken(phone, password);
        }


}
