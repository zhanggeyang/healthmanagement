package com.ithealth.controller;

import com.ithealth.constants.MessageConstant;
import com.ithealth.constants.RedisMessageConstant;
import com.ithealth.entity.Result;
import com.ithealth.pojo.Member;
import com.ithealth.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.controller
 * @ ClassName: OrderController
 * @ Author: 张戈扬
 * @ Date: 2019/8/4 15:53
 * @ Description: 登录控制层
 **/
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public Result doLogin(HttpServletResponse response, @RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        String userValidateCode = (String) map.get("validateCode");
        String jedisValidateCode = (String) redisTemplate.opsForValue().get(telephone);
        System.out.println("登陆验证码为"+jedisValidateCode);
        //String jedisValidateCode = jedisPool.getResource().get(RedisMessageConstant.SENDTYPE_LOGIN + telephone);
        if (!StringUtils.isEmpty(String.valueOf(jedisValidateCode))
                && !StringUtils.isEmpty(userValidateCode)
                && jedisValidateCode.equals(userValidateCode)) {
            Member member = memberService.isMember(telephone);
            if (member == null) {
                member = new Member();
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.doRegister(member);
            }
            Cookie cookie = new Cookie(RedisMessageConstant.SENDTYPE_LOGIN + telephone, telephone);
            cookie.setPath("/");
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);
            System.out.println(cookie.toString());
            return new Result(true, MessageConstant.LOGIN_SUCCESS, cookie);
        }
        return new Result(false, MessageConstant.VALIDATECODE_ERROR);
    }
}
