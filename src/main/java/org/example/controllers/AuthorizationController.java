package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.dao.Person;
import org.example.enums.RoleType;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/auth")
    public String getRegistrationPage(Model model, HttpServletRequest request) {
        Person user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole().equals(RoleType.OWNER)) {
            model.addAttribute("owner", user);
            return "redirect:/owneraccount";
        }
        model.addAttribute("client", user);
        return "redirect:/clientaccount";
    }

}