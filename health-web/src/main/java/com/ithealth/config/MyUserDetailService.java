package com.ithealth.config;

import com.ithealth.pojo.Permission;
import com.ithealth.pojo.Role;
import com.ithealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: health-management
 * @PackageName: com.ithealth.config
 * @ClassName: MyUserDetailService
 * @Date: 2021年01月26日 21:52
 * @Author: zhanggeyang
 * @Description:
 **/

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.ithealth.pojo.User user = userService.queryUserByName(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Set<Role> roleSet = user.getRoles();
        for (Role role : roleSet) {
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        System.out.println(authorities);
        return new User(username,user.getPassword(),authorities);
    }
}
