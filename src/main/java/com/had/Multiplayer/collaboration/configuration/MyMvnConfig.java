package com.had.Multiplayer.collaboration.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvnConfig
 * @Description TODO
 * @Author had
 * @Date 2021/1/13 21:56
 * @Version 1.0
 **/
@Configuration
public class MyMvnConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/b").setViewName("/aa");
    }
}
