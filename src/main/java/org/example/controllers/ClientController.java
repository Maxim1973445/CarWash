package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.example.dao.Person;
import org.example.enums.LogStatus;
import org.example.enums.RoleType;
import org.example.service.Impl.LogServiceImpl;
import org.example.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@Log
public class ClientController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    LogServiceImpl logService;

    @GetMapping(value = "/clientaccount")
    public String clientAccount(Model model) {
        model.addAttribute("client",
                userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        log.info("Отображение страницы личного кабинета клиента");
        return "clientaccount";
    }

    @PostMapping(value = "/addclient")
    public String add(Model model, HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("birthdate"));
        Person client = new Person(
                (long)(Math.random()*1000),
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                date,
                null,
                null,
                RoleType.CLIENT,
                null
        );
        userService.createUser(client);
        model.addAttribute("client", client);
        String message = "Создание нового пользователя под ролью клиента с логином " + client.getLogin();
        log.info(message);
        logService.writeLog(message, LogStatus.INFO, "создание пользователя");
        return "redirect:/authorization";
    }
}
