package com.ljs.reggie.service.impl;

import com.ljs.reggie.mapper.SetmealMapper;
import com.ljs.reggie.service.DishService;
import com.ljs.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 22:32
 */
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 根据分类id查关联的套餐数目
     * @param ids
     * @return
     */
    @Override
    public int countByCateId(Long ids) {
        int count = setmealMapper.countByCateId(ids);
        return count;
    }
}