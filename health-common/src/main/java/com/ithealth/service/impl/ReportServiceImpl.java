package com.ithealth.service.impl;

import com.ithealth.service.MemberService;
import com.ithealth.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ ProjectName: health_parent
 * @ PackageName: cn.itheima.service.impl
 * @ ClassName: ReportServiceImpl
 * @ Author: 张戈扬
 * @ Date: 2019/8/8 16:26
 * @ Description: 统计会员实现类
 **/
@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberService reportService;

    @Override
    public Map getMemberReport() {

        return null;
    }
}
