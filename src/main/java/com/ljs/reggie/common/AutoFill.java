package com.ljs.reggie.common;



import com.alibaba.fastjson.JSONPatch;
import com.ljs.reggie.common.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能：自定义注解，用于标识方法需要进行公共字段的自动填充处理
 * 作者：ljs
 * 日期：2024/4/13 22:10
 */
@Target(ElementType.METHOD)//该注解只能加在方法上
@Retention(RetentionPolicy.RUNTIME)//固定写法
public @interface AutoFill {
    //数据库操作类型：update insert
    OperationType value();
}