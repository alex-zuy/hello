package com.zuy.exam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping
    public String hello(Model model) {
        model.addAttribute("name", "Lexa");
        return "hello";
    }
}
