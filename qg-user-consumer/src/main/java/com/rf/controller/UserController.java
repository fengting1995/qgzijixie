package com.rf.controller;

import com.rf.dto.ReturnResult;
import com.rf.service.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class UserController {

        @Autowired
        private LocalUserService localUserService;


        @RequestMapping(value = "/doLogin",method = RequestMethod.GET)
        public ReturnResult doLogin(String phone, String password, HttpServletResponse response) throws Exception{

//              response.setHeader("Access-Control-Allow-Origin","*");//允许跨域,后面前端模块用的端口是8888，端口不同，所以要允许跨域
//                try {
                      return localUserService.validateToken(phone, password);
//                } catch (Exception e) {
//                        e.printStackTrace();
//                        return ReturnResultUtils.returnFail(CommonException.SYSTEM_EXCEPTION.getCode(),CommonException.SYSTEM_EXCEPTION.getMessage());
//                }
        }

        @RequestMapping(value = "/v/loginOut",method = RequestMethod.POST)
        public  ReturnResult loginOut(String token)throws Exception{
            return localUserService.removeToken(token);
        }





}
