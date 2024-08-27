package com.ljs.reggie.mapper;

import com.github.pagehelper.Page;
import com.ljs.reggie.common.AutoFill;
import com.ljs.reggie.common.enumeration.OperationType;
import com.ljs.reggie.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {
    /**
     * 根据分类id查关联的套餐数目
     * @param ids
     */
    @Select("select count(id) from setmeal where category_id = #{ids}")
    int countByCateId(Long ids);
}
