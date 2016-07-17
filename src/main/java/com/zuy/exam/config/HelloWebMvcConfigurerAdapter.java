package com.zuy.exam.config;

import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
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
        resolver.setSuffix(".vm");
        resolver.setViewClass(VelocityView.class);
        return resolver;
    }

    @Bean
    public VelocityConfigurer velocityConfigurer() {
        final VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("classpath:com/zuy/exam/views/");
        return velocityConfigurer;
    }
}
