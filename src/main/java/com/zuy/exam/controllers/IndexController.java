package com.zuy.exam.controllers;

import com.zuy.exam.models.LoginForm;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        if(isUserLoggedIn(authentication)) {
            model.addAttribute("userName", authentication.getName());
            return "greeting";
        }
        else {
            model.addAttribute("form", new LoginForm());
            return "login";
        }
    }

    private static boolean isUserLoggedIn(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
