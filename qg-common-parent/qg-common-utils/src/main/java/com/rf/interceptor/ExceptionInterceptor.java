package com.rf.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.rf.dto.ReturnResult;
import com.rf.dto.ReturnResultUtils;
import com.rf.exception.CommonException;
import com.rf.utils.EmptyUtils;
import com.rf.utils.PrintUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if(EmptyUtils.isNotEmpty(ex)){
            response.setContentType("text/html;charset=utf-8");
            PrintUtil printUtil=new PrintUtil(response);
            ReturnResult returnResult=ReturnResultUtils.returnFail(CommonException.SYSTEM_EXCEPTION.getCode(),CommonException.SYSTEM_EXCEPTION.getMessage());
            printUtil.print(JSONObject.toJSON(returnResult));//response改成输出为自定义错误类型，原本是一大堆的那种详细错误。
        }

    }
}
