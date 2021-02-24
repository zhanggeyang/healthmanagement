package com.ithealth.mapper;

import com.ithealth.pojo.Member;
import com.ithealth.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    /**
     * 查询用户是否存在
     * @param telephone
     * @return
     */
    Member isMember(String telephone);

    /**
     * 用户注册
     * @param member
     */
    void doRegister(Member member);

    /**
     * 用月份集合查询对应的每月会员数量
     * @param month
     * @return
     */
    Integer queryCountByMonth(String month);


}
