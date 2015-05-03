package com.tyhyidon.faust.game;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vasylsavytskyi on 30.04.15.
 */
@SpringBootApplication
public class ApplicationWeb extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/newindex").setViewName("newindex");
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public PropertiesFactoryBean ratingProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("rating.properties"));
        return bean;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationWeb.class, args);
    }



}

