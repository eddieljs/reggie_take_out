package com.ljs.reggie.mapper;

import com.github.pagehelper.Page;
import com.ljs.reggie.common.AutoFill;
import com.ljs.reggie.common.enumeration.OperationType;
import com.ljs.reggie.entity.Category;
import com.ljs.reggie.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

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

    /**
     * 分类分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    Page<Category> pageQuery(int page, int pageSize, String name);

    /**
     * 根据id查找分类
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category getById(Long id);

    /**
     * 更新分类信息
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    /**
     * 根据id删除分类
     * @param ids
     */
    @Delete("delete from category where id = #{ids}")
    void deleteById(Long ids);
}
