package com.ljs.reggie.common;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 22:56
 */

/**
 * 自定义业务异常
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }

}