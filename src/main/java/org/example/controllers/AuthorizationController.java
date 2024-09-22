package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.example.dao.Person;
import org.example.enums.RoleType;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/auth")
    public String getRegistrationPage(Model model) {
        log.info("Выбор направления редиректа исходя из роли пользователя");
        Person user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole().equals(RoleType.OWNER)) {
            model.addAttribute("owner", user);
            log.info("Переход в личный кабинет владельца");
            return "redirect:/owneraccount";
        }
        model.addAttribute("client", user);
        log.info("Переход в личный кабинет клиента");
        return "redirect:/clientaccount";
    }

}