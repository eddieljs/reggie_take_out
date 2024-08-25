package com.ljs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import java.security.Provider;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/24 13:04
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

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

}
