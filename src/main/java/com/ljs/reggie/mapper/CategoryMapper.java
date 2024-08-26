package com.ljs.reggie.mapper;

import com.ljs.reggie.common.AutoFill;
import com.ljs.reggie.common.enumeration.OperationType;
import com.ljs.reggie.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(id,type, name, sort, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " (#{id},#{type}, #{name}, #{sort}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Category category);
}
