package com.zuy.exam.controllers;

import com.zuy.exam.forms.LoginForm;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final String GREETING_VIEW = "greeting";

    private static final String LOGIN_VIEW = "login";

    private static final String FORM_ATTRIBUTE = "form";

    private static final String USER_NAME_ATTRIBUTE = "userName";

    private static final String HAS_ADMIN_ROLE_ATTRIBUTE = "hasAdminRole";

    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        if(isUserLoggedIn(authentication)) {
            model.addAttribute(USER_NAME_ATTRIBUTE, authentication.getName());
            model.addAttribute(HAS_ADMIN_ROLE_ATTRIBUTE, isUserHasAdminRole(authentication));
            return GREETING_VIEW;
        }
        else {
            model.addAttribute(FORM_ATTRIBUTE, new LoginForm());
            return LOGIN_VIEW;
        }
    }

    private static boolean isUserLoggedIn(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }

    private static boolean isUserHasAdminRole(Authentication authentication) {
        if(authentication == null) {
            return false;
        }
        else {
            return authentication.getAuthorities().stream()
                .anyMatch(authority -> ROLE_ADMIN.equals(authority.getAuthority()));
        }
    }
}
