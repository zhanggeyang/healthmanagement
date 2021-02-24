package com.ithealth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: health-management
 * @PackageName: com.ithealth.config
 * @ClassName: WebMvcConfig
 * @Date: 2021年01月26日 20:42
 * @Author: zhanggeyang
 * @Description:
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.html");
    }
}
