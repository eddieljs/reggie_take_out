package com.ljs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.common.R;
import com.ljs.reggie.entity.Employee;
import com.ljs.reggie.mapper.EmployeeMapper;
import com.ljs.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.Provider;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/24 13:04
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param employee
     * @return
     */
    @Override
    public Employee login(Employee employee) {
        String username = employee.getUsername();
        Employee getEmployee = employeeMapper.getByUsername(username);
        return getEmployee;
    }

    /**
     * 新增员工
     * @param employee
     */
    @Override
    public void add(Employee employee) {
        //设置初始密码 并进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置其他值
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setStatus(0);
        employeeMapper.add(employee);
    }
    /**
     * 员工分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageResult pageQuery(int page, int pageSize, String name) {
        PageHelper.startPage(page,pageSize);
        Page<Employee> pageResult = employeeMapper.pageQuery(page,pageSize,name);
        long total = pageResult.getTotal();
        List<Employee> result = pageResult.getResult();
        return new PageResult(total,result);
    }

}
