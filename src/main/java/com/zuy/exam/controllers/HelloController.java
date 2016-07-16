package com.zuy.exam.controllers;

import com.zuy.exam.repositories.UsersDao;
import com.zuy.exam.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UsersDao usersDao;

    @RequestMapping
    public String hello(Model model) {
        final User user = usersDao.getUser(1);
        model.addAttribute("user", user);
        return "hello";
    }
}
