package com.ljs.reggie.service.impl;

import com.ljs.reggie.mapper.DishMapper;
import com.ljs.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 22:32
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    /**
     * 根据分类id查关联的菜品数目
     * @param ids
     * @return
     */
    @Override
    public int countByCateId(Long ids) {
        int count = dishMapper.countByCateId(ids);
        return count;
    }
}