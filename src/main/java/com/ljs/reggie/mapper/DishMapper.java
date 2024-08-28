package com.ljs.reggie.mapper;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.github.pagehelper.Page;
import com.ljs.reggie.common.AutoFill;
import com.ljs.reggie.common.dto.DishDto;
import com.ljs.reggie.common.dto.DishPageQueryDTO;
import com.ljs.reggie.common.enumeration.OperationType;
import com.ljs.reggie.common.vo.DishVO;
import com.ljs.reggie.entity.Category;
import com.ljs.reggie.entity.Dish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {


    /**
     * 根据分类id查关联的套餐数目
     * @param ids
     */
    @Select("select count(id) from dish where category_id = #{ids}")
    int countByCateId(Long ids);

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void saveWithFlavor(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 更新菜品
     * @param dish
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 找到出售中菜品数量
     * @param id
     * @return
     */
    @Select("select count(*) from dish where id = #{id} and status = 1")
    int getSealCount(Long id);

    /**
     * 根据id删除菜品
     * @param id
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);
}
