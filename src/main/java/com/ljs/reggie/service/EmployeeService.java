package com.ljs.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.entity.Employee;

public interface EmployeeService{
    /**
     * 员工登录
     * @param employee
     * @return
     */
    Employee login(Employee employee);

    /**
     * 新增员工
     * @param employee
     */
    void add(Employee employee);

    /**
     * 员工分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    PageResult pageQuery(int page, int pageSize, String name);

    /**
     * 根据员工id修改信息
     * @param employee
     */
    void updateById(Employee employee);

    /**
     * 根据id查找员工
     * @param id
     * @return
     */
    Employee getById(Long id);
}
