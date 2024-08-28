package com.ljs.reggie.service;

import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增分类
     * @param category
     */
    void save(Category category);

    /**
     * 分类分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    PageResult pageQuery(int page, int pageSize, String name);

    /**
     * 根据id查找分类
     * @param id
     * @return
     */
    Category getById(Long id);

    /**
     * 分类信息修改
     * @param category
     */
    void updateById(Category category);

    /**
     * 根据id删除分类
     * @param ids
     */
    void removeById(Long ids);

    /**
     * 条件查询分类数据
     * @param category
     * @return
     */
    List<Category> list(Category category);
}
