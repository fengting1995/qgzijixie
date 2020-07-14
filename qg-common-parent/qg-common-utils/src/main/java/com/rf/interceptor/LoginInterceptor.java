package com.rf.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rf.dto.ReturnResult;
import com.rf.dto.ReturnResultUtils;
import com.rf.exception.CommonException;
import com.rf.utils.EmptyUtils;
import com.rf.utils.PrintUtil;
import com.rf.utils.RedisUtils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");//所有的拦截器都要设置允许跨域？
        String token = request.getParameter("token");//检测是否已有token 即是否已登录
        PrintUtil printUtil = null;
        String qgUserJson = null;
        ReturnResult returnResult = null;
        if(EmptyUtils.isEmpty(token)){
          returnResult =  ReturnResultUtils.returnFail(CommonException.USER_NO_LOGIN.getCode(),CommonException.USER_NO_LOGIN.getMessage());
        }else{
            //客户端有带来token，说明之前登录过，接下来判断是否登录过时，即token是否还在。
            qgUserJson = redisUtils.getStr(token);//根据之前存的找出这个token对应哪个用户
            if(EmptyUtils.isEmpty(qgUserJson)){
                returnResult =  ReturnResultUtils.returnFail(CommonException.USER_LOGIN_TIMEOUT_EXCEPTION.getCode(),CommonException.USER_LOGIN_TIMEOUT_EXCEPTION.getMessage());
            }
        }

        if(EmptyUtils.isNotEmpty(returnResult)){
            response.setContentType("text/html;charset=GBK");
            printUtil = new PrintUtil(response);
            printUtil.print(JSONObject.toJSON(returnResult));
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
