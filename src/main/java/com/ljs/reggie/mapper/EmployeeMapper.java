package com.ljs.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljs.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 根据用户名查密码
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
}
