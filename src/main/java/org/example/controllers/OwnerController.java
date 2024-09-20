package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.dao.Person;
import org.example.enums.RoleType;
import org.example.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class OwnerController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/registrationowner")
    public String registrationOwner() {
        return "registrationowner";
    }

    @PostMapping(value = "/addowner")
    public String add(Model model, HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("birthdate"));
        Person owner = new Person(
                (long)(Math.random()*1000),
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                date,
                null,
                null,
                RoleType.OWNER,
                null
        );
        userService.createUser(owner);
        model.addAttribute("owner", owner);
        return "redirect:/authorization";
    }

    @GetMapping(value = "/owneraccount")
    public String ownerAccount(Model model) {
        model.addAttribute("owner",
                userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "owneraccount";
    }
}
