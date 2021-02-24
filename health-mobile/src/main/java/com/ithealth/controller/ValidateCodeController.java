package com.ithealth.controller;

import com.ithealth.constants.MessageConstant;
import com.ithealth.constants.RedisMessageConstant;
import com.ithealth.entity.Result;
import com.ithealth.utils.SMSUtils;
import com.ithealth.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.controller
 * @ ClassName: ValidateCodeController
 * @ Author: 张戈扬
 * @ Date: 2019/8/4 15:40
 * @ Description: 验证码控制层
 **/
@RestController
@RequestMapping(value = "/validateCode")
public class ValidateCodeController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/validate4Order")
    public Result Validate4Order(String telephone) {
        String validateCode = String.valueOf(ValidateCodeUtils.generateValidateCode(4));
        //给用户发送验证码
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, validateCode);
            System.out.println("发送的预约验证码为" + validateCode);
            System.out.println(RedisMessageConstant.SENDTYPE_ORDER+telephone);
            redisTemplate.opsForValue().set(RedisMessageConstant.SENDTYPE_ORDER+telephone,validateCode,3*60);
            //jedisPool.getResource().setex(RedisMessageConstant.SENDTYPE_ORDER + telephone, 3 * 60, validateCode);
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }

    @RequestMapping(value = "send4Login",method = RequestMethod.POST)
    public Result Validate4Login(String telephone){
        String validateCode = String.valueOf(ValidateCodeUtils.generateValidateCode(4));
        try {
            System.out.println("发送的登陆验证码为" + validateCode);
            //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,validateCode);
            redisTemplate.opsForValue().set(telephone,validateCode,3*60);
            Object o = redisTemplate.opsForValue().get(telephone);
            System.out.println(o);
            //jedisPool.getResource().setex(RedisMessageConstant.SENDTYPE_LOGIN + telephone,5*60,validateCode);
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
