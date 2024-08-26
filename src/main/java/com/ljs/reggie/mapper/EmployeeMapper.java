package com.ljs.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.ljs.reggie.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper{
    /**
     * 根据用户名查密码
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    @Insert("insert into employee (name, username, password, phone, sex, id_number, status, " +
            "create_time, update_time, create_user, update_user) values" +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime}" +
            ",#{updateTime},#{createUser},#{updateUser}) ")
    void add(Employee employee);

    /**
     * 员工分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    Page<Employee> pageQuery(int page, int pageSize, String name);
}
