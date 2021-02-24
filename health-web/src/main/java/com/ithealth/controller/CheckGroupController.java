package com.ithealth.controller;

import com.ithealth.constants.MessageConstant;
import com.ithealth.entity.PageResult;
import com.ithealth.entity.QueryPageBean;
import com.ithealth.entity.Result;
import com.ithealth.pojo.CheckGroup;
import com.ithealth.pojo.CheckItem;
import com.ithealth.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.controller
 * @ ClassName: CheckGroupController
 * @ Author: 张戈扬
 * @ Date: 2019/7/29 15:31
 * @ Description: 检查组的controller
 **/
@RestController
@RequestMapping(value = "checkGroup")
public class CheckGroupController {
    @Autowired
    private CheckGroupService checkGroupService;

    @RequestMapping(value = "/findAllCheckGroup",method = RequestMethod.POST)
    public Result findAllCheckGroup() {
        //return new Result(true,"hello");
        List<CheckGroup> checkGroupList = checkGroupService.findAllCheckGroup();
        if (checkGroupList != null && checkGroupList.size() > 0) {
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupList);
        } else {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
    /**
     * 查询所有的检查项数据回显
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAllCheckItem() {
        List<CheckItem> checkItemList = checkGroupService.findAllCheckItem();
        if (checkItemList != null && checkItemList.size() > 0) {
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkItemList);
        } else {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 添加检查组和检查项
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody CheckGroup checkGroup, @RequestParam List<Integer> checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 检查组分页
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('CHECKGROUP_QUERY')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }

    /**
     * 根据ID查询检查组用于数据回显
     * @param id
     * @return
     */
    @RequestMapping(value = "/findCGById", method = RequestMethod.GET)
    public Result findCGById(Integer id) {
        CheckGroup checkGroup = checkGroupService.findCGById(id);
        if (checkGroup != null) {
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        }
        return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
    }

    /**
     * 查询当前选项卡检查组对应的所有的检查项信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/findCheckItemIdsByCheckGroupId", method = RequestMethod.GET)
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        List<Integer> checkItemList = checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return checkItemList;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(Integer id){
        try {
            checkGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    /**
     * 编辑检查组和建立新关系
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        try {
            checkGroupService.edit(checkGroup,checkitemIds);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }
}
