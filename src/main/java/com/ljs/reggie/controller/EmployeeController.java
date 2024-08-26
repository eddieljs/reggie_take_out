package com.ljs.reggie.controller;

import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.common.R;
import com.ljs.reggie.entity.Employee;
import com.ljs.reggie.service.EmployeeService;

import com.ljs.reggie.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


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

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工：{}",employee);
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setId(IdGenerator.nextId());
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeService.add(employee);
        return R.success("新增成功");
    }
    /**
     * 员工分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<PageResult> page(int page,int pageSize,String name){
        PageResult pageResult = employeeService.pageQuery(page,pageSize,name);

        return R.success(pageResult);
    }

    /**
     * 根据员工id修改信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        log.info("员工信息修改：{}",employee);
        return R.success("员工信息修改成功");
    }
}