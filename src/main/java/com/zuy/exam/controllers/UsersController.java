package com.zuy.exam.controllers;

import com.zuy.exam.entities.User;
import com.zuy.exam.models.UserDataForm;
import com.zuy.exam.services.UsersService;
import com.zuy.exam.services.UsersService.UsersServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private static final String REDIRECT_TO_INDEX = "redirect:/users";

    private static final String LIST_USERS_VIEW = "users/index";

    private static final String CREATE_USER_VIEW = "users/create";

    private static final String EDIT_USER_VIEW = "users/edit";

    private static final String FORM_ATTRIBUTE = "form";

    private static final String USER_ID_ATTRIBUTE = "userId";

    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", usersService.getUsers());
        return LIST_USERS_VIEW;
    }

    @GetMapping("/create")
    public String createUser(@ModelAttribute(FORM_ATTRIBUTE) UserDataForm form) {
        return CREATE_USER_VIEW;
    }

    @PostMapping("/create")
    public String storeUser(@Valid @ModelAttribute(FORM_ATTRIBUTE) UserDataForm form, BindingResult result) {
        if(result.hasErrors()) {
            return CREATE_USER_VIEW;
        }
        else {
            try {
                usersService.createUser(getUserFromForm(form));
                return REDIRECT_TO_INDEX;
            } catch (UsersServiceException e) {
                result.rejectValue(e.getFieldName(), null, e.getMessage());
                return CREATE_USER_VIEW;
            }
        }
    }

    @GetMapping("/{user_id}/edit")
    public String editUser(@PathVariable("user_id") int userId, Model model) {
        final User user = usersService.getUser(userId);
        model.addAttribute(FORM_ATTRIBUTE, getUserDataForm(user));
        model.addAttribute(USER_ID_ATTRIBUTE, userId);
        return EDIT_USER_VIEW;
    }

    @PostMapping("/{user_id}/edit")
    public String updateUser(@PathVariable("user_id") int userId, Model model,
        @Valid @ModelAttribute(FORM_ATTRIBUTE) UserDataForm form, BindingResult result) {

        if(result.hasErrors()) {
            model.addAttribute(USER_ID_ATTRIBUTE, userId);
            return EDIT_USER_VIEW;
        }
        else {
            try {
                final User user = getUserFromForm(form);
                user.setId(userId);
                usersService.updateUserCredentials(user);
                return REDIRECT_TO_INDEX;
            }
            catch (UsersServiceException e) {
                result.rejectValue(e.getFieldName(), null, e.getMessage());
                return EDIT_USER_VIEW;
            }
        }
    }

    @PostMapping("/delete")
    public String deleteUsers(@RequestParam("user_ids[]")int[] ids, RedirectAttributes attributes) {
        try {
            usersService.deleteUsers(ids);
        }
        catch (UsersServiceException e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return REDIRECT_TO_INDEX;
    }

    private User getUserFromForm(UserDataForm form) {
        final User user = new User();
        user.setLogin(form.getLogin());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        return user;
    }

    private UserDataForm getUserDataForm(User user) {
        final UserDataForm form = new UserDataForm();
        form.setLogin(user.getLogin());
        return form;
    }
}
