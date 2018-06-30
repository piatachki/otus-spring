package com.example.otus;

import com.example.otus.config.ApplicationConfiguration;
import com.example.otus.service.Tester;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Tester tester = context.getBean("tester", Tester.class);
        tester.doTest();
    }

}
