package com.ljs.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ljs.reggie.common.CustomException;
import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.common.R;
import com.ljs.reggie.common.dto.DishDto;
import com.ljs.reggie.common.dto.DishPageQueryDTO;
import com.ljs.reggie.entity.Dish;
import com.ljs.reggie.entity.DishFlavor;
import com.ljs.reggie.service.DishFlavorService;
import com.ljs.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/28 9:02
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Resource
    private DishService dishService;
    @Resource
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info("新增菜品：{}",dishDto);
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public R<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);

        return R.success(pageResult);

    }

    /**
     * 根据id查询菜品和口味
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R<DishDto> getById(@PathVariable Long id){
        Dish dish = dishService.getById(id);
        List<DishFlavor> flavors = dishFlavorService.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        dishDto.setFlavors(flavors);
        return R.success(dishDto);
    }
    /**
     * 更新菜品和口味
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        log.info("更新菜品和口味：{}",dishDto);
        dishService.updateWithFlavor(dishDto);
        return R.success("更新菜品成功");
    }

    /**
     * 起售停售菜品
     * @return
     */
//    @PostMapping("/status/{status}")
//    public R<String> startOrStop(@PathVariable Integer status,Long ids){
//        log.info("起售停售菜品，{}",ids);
//        dishService.startOrStop(status,ids);
//        return R.success("更新状态成功");
//    }
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable Integer status, @RequestParam List<Long> ids) {
        log.info("status:{},ids:{}", status, ids);
        for (Long id : ids) {
            dishService.startOrStop(status,id);
        }
        return R.success("批量操作成功");
    }

    /**
     * 删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids:{}",ids);
        dishService.removeWithFlavor(ids);
        return R.success("菜品删除成功");
    }
}