package com.ljs.reggie.service.impl;

import cn.hutool.log.Log;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljs.reggie.common.CustomException;
import com.ljs.reggie.common.PageResult;
import com.ljs.reggie.common.dto.DishDto;
import com.ljs.reggie.common.dto.DishPageQueryDTO;
import com.ljs.reggie.common.vo.DishVO;
import com.ljs.reggie.entity.Dish;
import com.ljs.reggie.entity.DishFlavor;
import com.ljs.reggie.entity.Employee;
import com.ljs.reggie.mapper.DishFlavorMapper;
import com.ljs.reggie.mapper.DishMapper;
import com.ljs.reggie.service.DishService;
import com.ljs.reggie.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能：
 * 作者：ljs
 * 日期：2024/8/26 22:32
 */
@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

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
    /**
     * 新增菜品
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //插入菜品
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto,dish);
        dish.setId(IdGenerator.nextId());//雪花id
        dishMapper.saveWithFlavor(dish);
        Long dishId = dish.getId();//get菜品id
        //插入口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        if (flavors != null && flavors.size() > 0){
            flavors.forEach(dishFlavor -> {//遍历每个口味，并为其id赋值
                dishFlavor.setId(IdGenerator.nextId());
                dishFlavor.setDishId(dishId);//为口味中的菜品id赋值
//                dishFlavor.setCreateTime(LocalDateTime.now());
//                dishFlavor.setUpdateTime(LocalDateTime.now());
//                dishFlavor.setUpdateUser(1);
//                dishFlavor.setCreateUser(LocalDateTime.now());
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }


    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());

        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Dish getById(Long id) {
        Dish dish = dishMapper.getById(id);
        return dish;
    }

    /**
     * 更新菜品和口味
     * @param dishDto
     */
    @Override
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish
        log.info("更新菜品");
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto,dish);
        dishMapper.update(dish);

        //清理当前口味
        log.info("清除口味");
        List<DishFlavor> flavors = dishDto.getFlavors();
        dishFlavorMapper.deleteByDishId(dishDto.getId());

        //插入新口味
        log.info("插入口味");
        dishFlavorMapper.insertBatch(flavors);

    }

    /**
     * 起售停售菜品
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dishMapper.update(dish);
    }

    /**
     * 删除菜品
     * @param ids
     */
    @Transactional
    @Override
    public void removeWithFlavor(List<Long> ids) {
        int count = 0;
        for(Long id : ids){
            if(dishMapper.getSealCount(id) > 0)
                count++;
        }
        if (count > 0){
            //如果不能删除，抛出一个业务异常
            throw new CustomException("存在起售中的菜品，不能删除");
        }else {
            for(Long id : ids){
                dishMapper.deleteById(id);
                dishFlavorMapper.deleteByDishId(id);
            }

        }


    }
}