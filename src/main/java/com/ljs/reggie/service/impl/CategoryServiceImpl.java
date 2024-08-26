package com.ljs.reggie.service.impl;

import com.ljs.reggie.common.R;
import com.ljs.reggie.entity.Category;
import com.ljs.reggie.mapper.CategoryMapper;
import com.ljs.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 20:03
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增分类
     * @param category
     */
    @Override
    public void save(Category category) {
        categoryMapper.insert(category);
    }
}