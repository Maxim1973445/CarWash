package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    // Метод для отображения страницы авторизации
    @GetMapping("/")
    public String authorization(Model model) {
        return "authorization"; // Возвращает шаблон авторизации
    }

    // Метод для отображения страницы регистрации владельца автомойки
    @GetMapping("/registrationowner")
    public String registrationOwner(Model model) {
        return "registrationowner"; // Возвращает шаблон регистрации владельца автомойки
    }

    // Метод для отображения стандартной страницы регистрации
    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration"; // Возвращает стандартную страницу регистрации

    }

    // Метод для отображения стандартной страницы регистрации
    @GetMapping("/owneraccount")
    public String owneraccount(Model model) {
        return "owneraccount"; // Возвращает стандартную страницу регистрации

    }

    @GetMapping("/clientaccount")
    public String clientaccount(Model model) {
        return "clientaccount"; // Возвращает стандартную страницу регистрации

    }
}