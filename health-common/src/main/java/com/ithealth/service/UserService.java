package com.ithealth.service;

import com.ithealth.entity.PageResult;
import com.ithealth.entity.QueryPageBean;
import com.ithealth.pojo.User;

public interface UserService {
    /**
     * 查询用户是否存在
     * @param username
     * @return
     */
    User queryUserByName(String username);

    boolean addUser(User user);

    PageResult findPage(QueryPageBean queryPageBean);

}
