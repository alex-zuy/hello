package com.zuy.exam.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class HelloWebApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(final ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext context = getContext();
        final Dynamic registration = servletContext.addServlet("main", new DispatcherServlet(context));
        registration.addMapping("/*");
        registration.setLoadOnStartup(1);
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("com.zuy.exam");
        return context;
    }
}
