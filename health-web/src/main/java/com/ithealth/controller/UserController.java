package com.ithealth.controller;

import com.ithealth.constants.MessageConstant;
import com.ithealth.entity.PageResult;
import com.ithealth.entity.QueryPageBean;
import com.ithealth.entity.Result;
import com.ithealth.mapper.RoleDao;
import com.ithealth.pojo.Menu;
import com.ithealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.controller
 * @ ClassName: UserController
 * @ Author: 张戈扬
 * @ Date: 2019/8/7 20:43
 * @ Description: 管理员控制层
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/queryUser",method = RequestMethod.GET)
    public Result queryUser(){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user);
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }


    @PostMapping(value = "/queryMenusByUsername")
    public Result queryMenusByUsername(String username){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Menu> menuList = roleDao.getMenusByUsername(user.getUsername());
            for (int i = 0; i < menuList.size(); i++) {
                Integer id = menuList.get(i).getId();
                List<Menu> childMenus = roleDao.getChildById(id);
                menuList.get(i).setChildren(childMenus);
            }
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,menuList);
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }

    }

    @PostMapping(value = "/addUser")
    public Result addUser(@RequestBody com.ithealth.pojo.User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean res = userService.addUser(user);
        if (res){
            return new Result(true,MessageConstant.ADD_USER_SUCCESS);
        }
        return new Result(false,MessageConstant.ADD_USER_FAILURE);
    }


    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = userService.findPage(queryPageBean);
        return pageResult;
    }


}
