package com.zuy.exam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = Controller.class)
public class CsrfControllerAdvice {

    @Autowired
    private HttpServletRequest request;

    @ModelAttribute("_csrf")
    public CsrfToken csrfToken() {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
