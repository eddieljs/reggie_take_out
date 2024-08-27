package com.ljs.reggie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljs.reggie.common.CustomException;
import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.common.R;
import com.ljs.reggie.entity.Category;
import com.ljs.reggie.entity.Employee;
import com.ljs.reggie.mapper.CategoryMapper;
import com.ljs.reggie.service.CategoryService;
import com.ljs.reggie.service.DishService;
import com.ljs.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 20:03
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 新增分类
     * @param category
     */
    @Override
    public void save(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 分类分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageResult pageQuery(int page, int pageSize, String name) {
        PageHelper.startPage(page,pageSize);
        Page<Category> pageResult = categoryMapper.pageQuery(page,pageSize,name);

        long total = pageResult.getTotal();
        List<Category> result = pageResult.getResult();
        return new PageResult(total,result);

    }

    /**
     * 根据id查找分类
     * @param id
     * @return
     */
    @Override
    public Category getById(Long id) {
        Category category = categoryMapper.getById(id);
        return category;
    }

    /**
     * 分类信息修改
     * @param category
     */
    @Override
    public void updateById(Category category) {
        categoryMapper.update(category);
    }

    /**
     * 根据id删除分类
     * @param ids
     */
    @Override
    public void removeById(Long ids) {
        //id是否关联了菜品
        int DishCount = dishService.countByCateId(ids);
        if (DishCount > 0){
            throw new CustomException("当前分类存在关联菜品，无法删除");
        }
        //id是否关联了套餐
        int SetmealCount = setmealService.countByCateId(ids);
        if (SetmealCount > 0){
            throw new CustomException("当前分类存在关联套餐，无法删除");
        }
        //正常删除
        categoryMapper.deleteById(ids);
    }


}