package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.dao.Person;
import org.example.enums.RoleType;
import org.example.security.CustomUserDetailsService;
import org.example.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class ClientController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/clientaccount")
    public String clientAccount(Model model) {
        model.addAttribute("client",
                userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "clientaccount";
    }

    @PostMapping(value = "/addclient")
    public String add(Model model, HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("birthdate"));
        Person owner = new Person(
                (long)(Math.random()*1000),
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                date,
                null,
                null,
                RoleType.CLIENT,
                null,
                null
        );
        userService.createUser(owner);
        model.addAttribute("owner", owner);
        return "redirect:/authorization";
    }
}
