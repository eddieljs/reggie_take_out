package com.ljs.reggie.service;

import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.common.dto.DishDto;
import com.ljs.reggie.common.dto.DishPageQueryDTO;
import com.ljs.reggie.entity.Category;
import com.ljs.reggie.entity.Dish;

import java.util.List;

public interface DishService {
    /**
     * 根据分类id查关联的菜品数目
     * @param ids
     * @return
     */
    int countByCateId(Long ids);

    /**
     * 新增菜品
     * @param dishDto
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    Dish getById(Long id);

    /**
     * 更新菜品和口味
     * @param dishDto
     */
    void updateWithFlavor(DishDto dishDto);

    /**
     * 起售停售菜品
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 删除菜品
     * @param ids
     */
    void removeWithFlavor(List<Long> ids);
}
