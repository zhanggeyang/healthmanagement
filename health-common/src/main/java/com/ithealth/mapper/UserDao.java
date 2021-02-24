package com.ithealth.mapper;

import com.github.pagehelper.Page;
import com.ithealth.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    /**
     * 查询用户是否存在
     * @param username
     * @return
     */
    User queryUserByName(String username);

    Integer addUser(User user);

    Page<User> queryUserPageUser(String queryString);

}
