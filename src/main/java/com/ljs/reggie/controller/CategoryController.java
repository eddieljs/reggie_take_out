package com.ljs.reggie.controller;

import cn.hutool.core.util.IdUtil;
import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.common.R;
import com.ljs.reggie.entity.Category;
import com.ljs.reggie.entity.Employee;
import com.ljs.reggie.service.CategoryService;
import com.ljs.reggie.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     * 分类分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<PageResult> page(int page, int pageSize, String name){
        PageResult pageResult = categoryService.pageQuery(page,pageSize,name);

        return R.success(pageResult);
    }
    /**
     * 根据id查找分类
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Category> add(@PathVariable Long id){
        Category category = categoryService.getById(id);

        return category == null ? R.error("无法查询该用户") : R.success(category);
    }

    /**
     * 更新分类
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        log.info("分类信息修改：{}",category);
        return R.success("分类信息修改成功");
    }

    /**
     * 根据id删除分类
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids){
        log.info("删除分类：{}",ids);

        categoryService.removeById(ids);
        return R.success("删除成功");
    }

    /**
     * 条件查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        List<Category> list =  categoryService.list(category);
        return R.success(list);
    }
}