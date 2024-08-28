package com.ljs.reggie.common.dto;


import com.ljs.reggie.entity.Setmeal;
import com.ljs.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
