package com.spring;

import com.spring.config.AppConfig;
import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user = new User();
        user.setName("Bob");
        user.setEmail("@gmail.com");

        userService.add(user);

        System.out.println(userService.listUsers());
    }
}
