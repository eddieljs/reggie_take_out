package com.ljs.reggie.config;


import com.ljs.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.ExtendedMessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/23 16:47
 */
@Configuration
@Slf4j

public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 静态资源映射
     * @param registry
     */
    @Override

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //用到**文件时从后面的目录里找
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        log.info("静态资源映射成功。。。");

    }

    /**
     *扩展Spring MVC框架的消息转化器
     * @param converters
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器，对象转换器可以将Java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转化器加入容器中
        converters.add(0,converter);
    }
}