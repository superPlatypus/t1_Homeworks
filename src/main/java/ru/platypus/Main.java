package ru.platypus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.platypus.service.UserService;

@EnableAspectJAutoProxy
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.isUserExists("Tim"));
        System.out.println(userService.getAllUsers());
        userService.addRandomUsers();
        try {
            userService.addUser("");
        }
        catch (Exception e) {}

    }
}