package com.ljs.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljs.reggie.entity.Employee;

public interface EmployeeService extends IService<Employee> {
    /**
     * 员工登录
     * @param employee
     * @return
     */
    Employee login(Employee employee);
}
