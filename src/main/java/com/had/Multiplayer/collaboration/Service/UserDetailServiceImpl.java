package com.had.Multiplayer.collaboration.Service;

import com.had.Multiplayer.collaboration.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName UserDetailServiceImpl
 * @Description TODO
 * @Author had
 * @Date 2021/1/14 00:00
 * @Version 1.0
 **/
@Service
@Configuration
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.getUserByUserName(username);
        if(u == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetails user = new org.springframework.security.core.userdetails.User(username,u.getPassword(), Collections.emptyList());
        return user;
    }


}
