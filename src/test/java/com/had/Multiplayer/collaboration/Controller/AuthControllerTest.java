package com.had.Multiplayer.collaboration.Controller;

import com.alibaba.fastjson.JSONObject;
import com.had.Multiplayer.collaboration.Service.UserDetailServiceImpl;
import com.had.Multiplayer.collaboration.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    private MockMvc mvc;
    @Mock
    private UserDetailServiceImpl userDetailsService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    private void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(
                new AuthController(userDetailsService, authenticationManager, userService, encoder)).build();
    }

    @Test
    public void logout() throws Exception {
        mvc.perform(get("/auth/logout")).andExpect(status().isOk())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("fail")));
        Map<String, String> map = new HashMap<>();
        map.put("username", "had");
        map.put("password", "123456");
        //先登陆
        Mockito.when(userService.getUserByUserName("had")).thenReturn(
                new com.had.Multiplayer.collaboration.bean.User(123, "had", encoder.encode("123456")));

        Mockito.when(userDetailsService.loadUserByUsername("had"))
                .thenReturn(new User("had", encoder.encode("123456"), Collections.emptyList()));

        MvcResult mvcResult = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString(Charset.forName("UTF-8")).contains("登陆成功")))
                .andReturn();

        HttpSession session = mvcResult.getRequest().getSession();
        mvc.perform(get("/auth/logout").session((MockHttpSession) session)).andExpect(status().isOk())
                .andExpect(result ->
                        assertTrue(result.getResponse().getContentAsString(Charset.forName("UTF-8")).contains("注销成功"))
                );
    }

    @Test
    public void testLogin() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "had");
        map.put("password", "123456");
        //先登陆
        Mockito.when(userService.getUserByUserName("had")).thenReturn(
                new com.had.Multiplayer.collaboration.bean.User(123, "had", encoder.encode("123456")));

        Mockito.when(userDetailsService.loadUserByUsername("had"))
                .thenReturn(new User("had", encoder.encode("123456"), Collections.emptyList()));

        MvcResult mvcResult = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("登陆成功")))
                .andReturn();

        HttpSession session = mvcResult.getRequest().getSession();
        mvc.perform(get("/auth").session((MockHttpSession) session)).andExpect(status().isOk())
                .andExpect(result -> {
                    assertTrue(result.getResponse().getContentAsString().contains("had"));
                });
    }

    @Test
    public void logoutTest() throws Exception {
        //用户为null
        mvc.perform(get("/auth")).andExpect(result ->
                assertTrue(result.getResponse().getContentAsString().contains("ok"))
        );
        //匿名用户anonymousUser
//        Mockito.when(getContext().getAuthentication()).
//                thenReturn(new AnonymousAuthenticationToken("123123","anonymousUser",Collections.emptyList()));

        mvc.perform(get("/auth")).andExpect(status().isOk()).andExpect(result -> {
            System.out.println("123123");
            System.out.println(result.getResponse().getContentAsString());
            assertTrue(result.getResponse().getContentAsString().contains("false"));
        });
    }

    @Test
    public void registerTest() throws Exception {
        Map<String, String> paramRegister = new HashMap<>();
        paramRegister.put("username", "had");
        paramRegister.put("password", "password");

        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(paramRegister)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    System.out.println(result.getResponse().getContentAsString(Charset.forName("UTF-8")));
                    Assertions.assertTrue(result.getResponse().getContentAsString(Charset.forName("UTF-8")).contains("注册成功"));
                });

        paramRegister.put("username", "123asdhfu73hrwhiurhwehwieriwuerwer");
        paramRegister.put("password", "password");
        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(paramRegister)))
                .andExpect(status().isOk())
                .andExpect(result ->
                        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8)
                                .contains("用户名不符合规则,用户名只能长度1到15个字符，只能是字母数字下划线中文"))
                );

        paramRegister.put("username", "had");
        paramRegister.put("password", "123");
        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(paramRegister)))
                .andExpect(status().isOk())
                .andExpect(result ->
                        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8)
                                .contains("密码不符合规则,密码只能长度6到16个任意字符"))
                );

        paramRegister.put("username", "had");
        paramRegister.put("password", "123456");
        Mockito.when(userService.isRegisted("had")).thenReturn(true);
        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(paramRegister)))
                .andExpect(status().isOk())
                .andExpect(result ->
                        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8)
                                .contains("用户名已经被注册了,请重新注册"))
                );
    }

}