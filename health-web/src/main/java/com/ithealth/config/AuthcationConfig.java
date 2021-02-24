package com.ithealth.config;

import com.ithealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ProjectName: health-management
 * @PackageName: com.ithealth.config
 * @ClassName: AuthcationConfig
 * @Date: 2021年01月26日 20:14
 * @Author: zhanggeyang
 * @Description:
 **/
@EnableWebSecurity
public class AuthcationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeRequests().antMatchers("/pages/**").authenticated();

        http.authorizeRequests().and()
                .formLogin()
                .loginPage("/login.html")//设置登陆页面
                .loginProcessingUrl("/login")//设置登陆方法名，和form表单的action一致
                .defaultSuccessUrl("/pages/main.html")//登陆成功后跳转页面
                .usernameParameter("username")//从form表获取账号密码用于登陆
                .passwordParameter("password")
                .and()
                .headers()
                .frameOptions()
                .sameOrigin();//解决iframe不显示问题
        http.logout().logoutSuccessUrl("/login.html").deleteCookies().invalidateHttpSession(true);//退出成功页面

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

