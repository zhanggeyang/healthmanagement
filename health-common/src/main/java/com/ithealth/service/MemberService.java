package com.ithealth.service;

import com.ithealth.pojo.Member;

import java.util.Map;

public interface MemberService {
    /**
     * 检查用户是否为会员
     * @param telephone
     * @return
     */
    Member isMember(String telephone);

    /**
     * 处理用户登录
     * @param member
     */
    void doRegister(Member member);

    /**
     * 统计会员数量
     * @return
     */
    Map getMemberReport();


}
