package com.ithealth.controller;

import com.ithealth.constants.MessageConstant;
import com.ithealth.entity.PageResult;
import com.ithealth.entity.QueryPageBean;
import com.ithealth.entity.Result;
import com.ithealth.pojo.CheckGroup;
import com.ithealth.pojo.Setmeal;
import com.ithealth.service.CheckGroupService;
import com.ithealth.service.SetMealService;
import com.ithealth.utils.QiniuUtils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.controller
 * @ ClassName: SetMealController
 * @ Author: 张戈扬
 * @ Date: 2019/7/31 16:49
 * @ Description: 套餐控制层
 **/
@RestController
@RequestMapping(value = "/setmeal")
public class SetMealController {
    @Autowired
    private SetMealService setMealService;
    @Autowired
    private CheckGroupService checkGroupService;

    @RequestMapping(value = "/upload")
    public Result upload(MultipartFile imgFile) {
        try {
            String originalFilename = imgFile.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);
            String newName = UUID.randomUUID().toString() + "." + extension;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), newName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, newName);
        } catch (IOException e) {
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody Setmeal setmeal, @RequestParam List<Integer> checkgroupIds) {
        try {
            System.out.println(checkgroupIds);
            setMealService.add(setmeal, checkgroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public PageResult findPage(@RequestBody(required = false) QueryPageBean queryPageBean) {
        PageResult pageResult = setMealService.findPgae(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping(value = "/findSetmealById", method = RequestMethod.GET)
    public Result findSetmealById(Integer id) {
        try {
            Setmeal setmeal = setMealService.findSetMealById(id);
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    @RequestMapping(value = "/findAllCG",method = RequestMethod.GET)
    public Result findAllCG(){
        try {
            List<CheckGroup> allCheckGroup = checkGroupService.findAllCheckGroup();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,allCheckGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping(value = "/findCheckGroupBySetmealId", method = RequestMethod.GET)
    public Result findCheckGroupBySetmealId(Integer id) {
        try {
            List<Integer> checkGroupList = setMealService.findCheckGroupBySetmealId(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(Integer id){
        try {
            setMealService.delete(id);
            return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }
}
