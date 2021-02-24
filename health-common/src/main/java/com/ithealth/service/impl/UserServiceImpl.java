package com.ithealth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ithealth.entity.PageResult;
import com.ithealth.entity.QueryPageBean;
import com.ithealth.mapper.PermissionDao;
import com.ithealth.mapper.RoleDao;
import com.ithealth.mapper.UserDao;
import com.ithealth.pojo.CheckItem;
import com.ithealth.pojo.Permission;
import com.ithealth.pojo.Role;
import com.ithealth.pojo.User;
import com.ithealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.service.impl
 * @ ClassName: UserServiceImpl
 * @ Author: 张戈扬
 * @ Date: 2019/8/7 16:26
 * @ Description: 用户服务实现类
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;


    @Override
    public User queryUserByName(String username) {
        User user = userDao.queryUserByName(username);
        if (user != null) {
            //获取角色
            Set<Role> roleSet = roleDao.queryRolesByUID(user.getId());
            if (roleSet != null && roleSet.size() > 0) {
                for (Role role : roleSet) {
                    //获取权限
                    Set<Permission> permissionSet = permissionDao.queryPremissionByRoleID(role.getId());
                    //为角色授权
                    role.setPermissions(permissionSet);
                }
            }
            user.setRoles(roleSet);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        int i = userDao.addUser(user);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<User> user = userDao.queryUserPageUser(queryPageBean.getQueryString());
        PageInfo info = new PageInfo<User>(user.getResult());
        return new PageResult(info.getTotal(), info.getList());
    }
}
