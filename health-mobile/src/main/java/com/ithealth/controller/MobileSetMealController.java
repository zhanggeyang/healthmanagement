package com.ithealth.controller;

import com.ithealth.constants.MessageConstant;
import com.ithealth.entity.Result;
import com.ithealth.pojo.Setmeal;
import com.ithealth.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.controller
 * @ ClassName: MobileSetMealController
 * @ Author: 张戈扬
 * @ Date: 2019/8/2 15:42
 * @ Description: 移动端控制层
 **/
@RestController
@RequestMapping(value = "/mobilesetmeal")
public class MobileSetMealController {
    @Autowired
    private SetMealService setMealService;

    @RequestMapping(value = "/findAllSetmeal",method = RequestMethod.POST)
    public Result findAllSetmeal(){
        try {
            List<Setmeal> setmealList = setMealService.findAllSetMeal();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,setmealList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }

    @RequestMapping(value = "/findSetMealById",method = RequestMethod.POST)
    public Result findSetMealById(Integer id){
        try {
            Setmeal setmeal = setMealService.findSetMealById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
