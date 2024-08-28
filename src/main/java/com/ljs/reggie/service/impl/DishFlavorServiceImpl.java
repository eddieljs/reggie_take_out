package com.ljs.reggie.service.impl;

import com.ljs.reggie.common.dto.DishDto;
import com.ljs.reggie.entity.DishFlavor;
import com.ljs.reggie.mapper.DishFlavorMapper;
import com.ljs.reggie.mapper.DishMapper;
import com.ljs.reggie.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/28 9:01
 */
@Service
public class DishFlavorServiceImpl implements DishFlavorService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 据id查询菜品和口味
     * @param id
     * @return
     */
    @Override
    public List<DishFlavor> getById(Long id) {
        List<DishFlavor> flavors = dishFlavorMapper.getById(id);
        return flavors;
    }
}