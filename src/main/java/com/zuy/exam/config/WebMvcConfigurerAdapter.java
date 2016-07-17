package com.zuy.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;

@Configuration
@EnableWebMvc
@Import(ValidationConfig.class)
public class WebMvcConfigurerAdapter
    extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter {

    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;

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

    @Override
    public Validator getValidator() {
        return localValidatorFactoryBean;
    }
}
