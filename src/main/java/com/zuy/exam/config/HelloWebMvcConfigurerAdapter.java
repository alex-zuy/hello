package com.zuy.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;

@Configuration
@EnableWebMvc
public class HelloWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("com/zuy/exam/views/");
        resolver.setSuffix(".vm");
        resolver.setViewClass(VelocityView.class);
        return resolver;
    }

    @Bean
    public VelocityConfigurer velocityConfigurer() {
        final VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("/WEB-INF/views");
        return velocityConfigurer;
    }
}
