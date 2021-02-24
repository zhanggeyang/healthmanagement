package com.ithealth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * @ProjectName: health-management
 * @PackageName: com.ithealth
 * @ClassName: HealthManagementApplication
 * @Date: 2021年01月23日 23:59
 * @Author: zhanggeyang
 * @Description:
 **/

@SpringBootApplication

public class HealthWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthWebApplication.class, args);
    }
}
