package com.had.Multiplayer.collaboration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class MultiplayerCollaborationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplayerCollaborationApplication.class, args);
	}



	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("=================进入我的多人协作项目==================");
			String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanDefinitionNames);
			for(String beanName : beanDefinitionNames) {
				System.out.println(beanName);
			}
		};
	}
}
