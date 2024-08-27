package com.ljs.reggie.service;

import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.entity.Category;

public interface SetmealService {
    /**
     * 根据分类id查关联的套餐数目
     * @param ids
     * @return
     */
    int countByCateId(Long ids);
}
