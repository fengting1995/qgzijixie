package com.rf.dto;

    /*
    * 返回工具类
    * */
public class ReturnResultUtils {

    /*
    * 成功，不带数据
    * */
    public static ReturnResult returnSuccess(){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(0);
        return returnResult;
    }


        /*
         * 成功，带数据，附静态方法的参数不能用类上的泛型作为参数，原本这边是T data用不了。
         * */
    public static ReturnResult returnSuccess(Object data){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(0);
        returnResult.setData(data);
        return returnResult;
    }


        /*
         * 失败，则返回失败信息
         * */
    public static ReturnResult returnFail(Integer code,String message){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        return returnResult;
    }


}
