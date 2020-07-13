package com.rf.controller;

import com.rf.dto.ReturnResult;
import com.rf.dto.ReturnResultUtils;
import com.rf.exception.CommonException;
import com.rf.pojo.QgUser;
import com.rf.service.LocalUserService;
import com.rf.service.QgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


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
        public ReturnResult doLogin(String phone, String password, HttpServletResponse response) throws Exception{

//              response.setHeader("Access-Control-Allow-Origin","*");//允许跨域,后面前端模块用的端口是8888，端口不同，所以要允许跨域
//                try {
                      return localUserService.validateToken(phone, password);
//                } catch (Exception e) {
//                        e.printStackTrace();
//                        return ReturnResultUtils.returnFail(CommonException.SYSTEM_EXCEPTION.getCode(),CommonException.SYSTEM_EXCEPTION.getMessage());
//
//                }
        }




}
