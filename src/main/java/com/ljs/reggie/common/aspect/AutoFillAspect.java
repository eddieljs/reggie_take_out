package com.ljs.reggie.common.aspect;


import com.ljs.reggie.common.AutoFill;
import com.ljs.reggie.common.enumeration.OperationType;
import com.ljs.reggie.common.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 功能：自定义切面，实现公共字段自动填充处理逻辑
 * 作者：ljs
 * 日期：2024/4/13 22:24
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(* com.ljs.reggie.mapper.*.*(..)) && @annotation(com.ljs.reggie.common.AutoFill)")
    //execution锁定目标类 annotation要求满足方法上加入注解 才能为切入点
    public void autoFillPointCut(){}

    //定义前置通知，为公共字段赋值
    @Before("autoFillPointCut()")//todo AOP切面编程
    public void autoFill(JoinPoint joinPoint){
        log.info("开始进行公共字段自动填充。。。");

        //获取被拦截的方法的操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();//获得数据库操作类型
        //获取被拦截方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0){
            return;
        }

        Object entity = args[0];
        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //根据不同的操作类型，为对应的属性进行反射赋值
        if (operationType == OperationType.INSERT){
            //为四个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);

                //通过反射为对象属性赋值
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType == OperationType.UPDATE){
            //为两个赋值updatetime updatetimeuser
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);

                //通过反射为对象属性赋值
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}