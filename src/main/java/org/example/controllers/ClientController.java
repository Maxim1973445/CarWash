package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.example.dao.Car;
import org.example.dao.Order;
import org.example.dao.Person;
import org.example.enums.LogStatus;
import org.example.enums.RoleType;
import org.example.service.Impl.CarServiceImpl;
import org.example.service.Impl.LogServiceImpl;
import org.example.service.Impl.OrderServiceImpl;
import org.example.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@Log
public class ClientController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    LogServiceImpl logService;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    CarServiceImpl carService;

    @GetMapping(value = "/clientaccount")
    public String clientAccount(Model model) {
        Person client = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Car> cars = carService.getAllCars().stream().filter(e->e.getPerson().getId().equals(client.getId())).toList();
        List<Order> orders = orderService.getAllOrders().stream().filter(e->e.getCar().getPerson().getId().equals(client.getId())).toList();
        model.addAttribute("client", client);
        model.addAttribute("cars", cars);
        model.addAttribute("orders", orders);
        log.info("Отображение страницы личного кабинета клиента");
        return "clientaccount";
    }

    @PostMapping(value = "/addclient")
    public String add(Model model, HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("birthdate"));
        long count = userService.count();
        Person client = new Person(
                ++count,
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

    @PostMapping(value = "/addCar")
    public String addCar(HttpServletRequest request) {
        Person client = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        long count = carService.count();
        carService.createCar(
                new Car(
                        ++count,
                        request.getParameter("carNumber"),
                        request.getParameter("make"),
                        request.getParameter("model"),
                        carService.getType(request.getParameter("carType")),
                        null,
                        client
                ));
        return "redirect:/clientaccount";
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile(HttpServletRequest request) {
        Person client = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        client.setEmail(request.getParameter("email").isBlank() ? client.getEmail() : request.getParameter("email"));
        client.setPhone(request.getParameter("phone").isBlank() ? client.getPhone() : request.getParameter("phone"));
        client.setDateOfBirth(request.getParameter("birthday").isBlank() ? client.getDateOfBirth() : LocalDate.parse(request.getParameter("birthday")));
        userService.updateUser(client);
        String message = "Редактирование профиля с логином " + client.getLogin();
        log.info(message);
        logService.writeLog(message, LogStatus.INFO, "редактирование пользователя");
        return "redirect:/clientaccount";
    }
}
