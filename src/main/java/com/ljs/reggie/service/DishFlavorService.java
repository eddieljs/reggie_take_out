package com.ljs.reggie.service;

import com.ljs.reggie.common.dto.DishDto;
import com.ljs.reggie.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService {
    /**
     *
     * @param id
     * @return
     */
    List<DishFlavor> getById(Long id);
}
