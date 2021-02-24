package com.ithealth.controller;

import com.ithealth.constants.MessageConstant;
import com.ithealth.entity.Result;
import com.ithealth.service.MemberService;
import com.ithealth.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.controller
 * @ ClassName: ReportController
 * @ Author: 张戈扬
 * @ Date: 2019/8/8 16:18
 * @ Description: 统计控制类
 **/
@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    private SetMealService setMealService;
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/getSetmealReport", method = RequestMethod.GET)
    public Result getSetmealReport() {
        try {
            Map map = setMealService.getSetmealReport();
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    @RequestMapping(value = "/getMemberReport",method = RequestMethod.GET)
    public Result getMemberReport() {
        try {
            Map map = memberService.getMemberReport();
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }
}
