package com.had.Multiplayer.collaboration.Controller;

import com.had.Multiplayer.collaboration.Service.UserDetailServiceImpl;
import com.had.Multiplayer.collaboration.Service.UserService;
import com.had.Multiplayer.collaboration.bean.Result;
import com.had.Multiplayer.collaboration.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author had
 * @Date 2021/1/11 13:34
 * @Version 1.0
 **/
@RestController
public class AuthController {
    //用户名数字英文汉字和下划线，1-15位
    public static final Pattern nameRegex = Pattern.compile("^[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,15}$");
    //密码6-15位字符
    public static final Pattern passwordRegex = Pattern.compile(".{6,15}");

    private UserDetailServiceImpl userDetailsService;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private BCryptPasswordEncoder encoder;

    public AuthController(UserDetailServiceImpl userDetailsService, AuthenticationManager authenticationManager, UserService userService, BCryptPasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
    }

    //获取当前登录的用户名，如果没有登陆则返回anonymousUser
    public static String getThreadUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : authentication.getName();
    }

    @GetMapping("/auth/logout")
    public Result logout() {
        String name = AuthController.getThreadUserName();
        User user = userService.getUserByUserName(name);
        if (user == null) {
            return new Result("fail", "用户尚未登录");
        }
        SecurityContextHolder.clearContext();
        return new Result("fail", "注销成功");
    }

    @GetMapping("/auth")
    public Result auth() {
        String name = AuthController.getThreadUserName();
        if (null == name || name.contains("anonymousUser")) {
            return Result.successResult("ok", false);
        }
        User user = userService.getUserByUserName(name);
        return Result.successResult("ok", user, true);
    }

    @PostMapping("/auth/register")
    public Result register(@RequestBody Map<String, String> paramRegister) {
        String username = paramRegister.get("username");
        String password = paramRegister.get("password");
        if (!nameRegex.matcher(username).find()) {
            return Result.failResult("用户名不符合规则,用户名只能长度1到15个字符，只能是字母数字下划线中文");
        }

        if (!passwordRegex.matcher(password).find()) {
            return Result.failResult("密码不符合规则,密码只能长度6到16个任意字符");
        }
        //检验用户名是否已经被注册了
        if (userService.isRegisted(username)) {
            return Result.failResult("用户名已经被注册了,请重新注册");
        }
        //注册
        User user = new User();
        user.setUserName(username);
        user.setPassword(encoder.encode(password));
        user.setCreateAt(Instant.now());
        user.setUpdateAt(Instant.now());
        user.setAvatar("http://localhost:8080/static/fonts/c3.png");
        try {
            userService.saveUser(user);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return Result.failResult("注册失败");
        }
        return Result.successResult("ok", "注册成功", user);
    }

    @PostMapping("/auth/login")
    public Object login(@RequestBody Map<String, String> paramLogin) {
        String username = paramLogin.get("username");
        String password = paramLogin.get("password");
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return Result.failResult("用户名不正确");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
                password, userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (BadCredentialsException e) {
            return Result.failResult("密码不正确");
        }
        return Result.successResult("ok", "登陆成功", userService.getUserByUserName(username));
    }

    @GetMapping("/b")
    public String getB() {
        return "b";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

}
