package com.had.Multiplayer.collaboration.Controller;

import com.had.Multiplayer.collaboration.Service.BlogService;
import com.had.Multiplayer.collaboration.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class BlogControllerTest {
    private MockMvc mvc;
    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserService userService;

    @Mock
    private BlogService blogService;

    @InjectMocks
    private BlogController blogController;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new BlogController(userService, blogService)).build();
    }

    @Test
    public void getBlogById() throws Exception {
        mvc.perform(get("/blog/1")).andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("获取成功"));
    }


    @Test
    public void getBlogTest() throws Exception {
        mvc.perform(get("/blog?page=1&pageSize=1&atIndex=true")).andExpect(status().isOk())
                .andExpect(result ->
                        Assertions.assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("获取成功")));
    }
}