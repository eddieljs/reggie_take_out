package com.ljs.reggie.controller;

import cn.hutool.core.util.IdUtil;
import com.ljs.reggie.common.R;
import com.ljs.reggie.entity.Category;
import com.ljs.reggie.service.CategoryService;
import com.ljs.reggie.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 20:02
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        category.setId(IdUtil.getSnowflakeNextId());
        categoryService.save(category);


        return R.success("新增分类成功");
    }
}