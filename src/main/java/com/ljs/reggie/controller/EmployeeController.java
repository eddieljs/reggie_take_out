package com.ljs.reggie.controller;

import com.ljs.reggie.common.R;
import com.ljs.reggie.entity.Employee;
import com.ljs.reggie.properties.JwtProperties;
import com.ljs.reggie.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/24 13:10
 */
@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //对密码进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        //调用service方法查询用户
        Employee emp = employeeService.login(employee);
        if (emp == null){
            return R.error("登陆失败");
        }

        //比对密码
        if (!emp.getPassword().equals(password)){
            return R.error("登陆失败");
        }

        //查看员工状态
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //登录成功
        request.getSession().setAttribute("employee",emp.getId());


        return R.success(emp);
    }
    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

}