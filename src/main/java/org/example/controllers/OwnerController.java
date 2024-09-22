package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.example.dao.Person;
import org.example.dao.Station;
import org.example.enums.LogStatus;
import org.example.enums.RoleType;
import org.example.service.Impl.LogServiceImpl;
import org.example.service.Impl.StationServiceImpl;
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
public class OwnerController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    StationServiceImpl stationService;

    @Autowired
    LogServiceImpl logService;

    @GetMapping(value = "/registrationowner")
    public String registrationOwner() {
        log.info("Отображение страницы регистрации владельца");
        return "registrationowner";
    }

    @PostMapping(value = "/addowner")
    public String add(Model model, HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("birthdate"));
        Person owner = new Person(
                (long) (Math.random() * 1000),
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
        String message = "Создание нового пользователя под ролью владельца с логином " + owner.getLogin();
        log.info(message);
        logService.writeLog(message, LogStatus.INFO, "создание пользователя");
        return "redirect:/authorization";
    }

    @GetMapping(value = "/owneraccount")
    public String ownerAccount(Model model) {
        Person owner = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Station> stations = stationService.getStations().stream().filter(e -> e.getPerson().equals(owner)).toList();
        model.addAttribute("owner", owner);
        model.addAttribute("stations", stations);
        log.info("Отображение страницы личного кабинета владельца");
        return "owneraccount";
    }

    @PostMapping(value = "/owneraccount/update")
    public String updateAccount(HttpServletRequest request) {
        Person owner = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        owner.setEmail(request.getParameter("email").isBlank() ? request.getParameter("email") : "");
        owner.setPhone(request.getParameter("phone").isBlank() ? request.getParameter("phone") : "");
        owner.setEmail(request.getParameter("birthday").isBlank() ? request.getParameter("birthday") : "");
        userService.updateUser(owner);
        String message = "Редактирование профиля с логином " + owner.getLogin();
        log.info(message);
        logService.writeLog(message, LogStatus.INFO, "редактирование пользователя");
        return "redirect:/owneraccount";
    }

    @PostMapping(value = "/owneraccount/addstation")
    public String addStation(HttpServletRequest request) {
        Person owner = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Station station = new Station(
                (long) (Math.random() * 1000),
                request.getParameter("carWashName"),
                null,
                null,
                request.getParameter("carWashAddress"),
                null,
                null,
                null,
                null,
                null,
                null,
                owner
        );
        stationService.createStation(station);
        String message = "Создание новой станции с названием " + station.getStationName();
        log.info(message);
        logService.writeLog(message, LogStatus.INFO, "создание станции");
        return "redirect:/owneraccount";
    }


}
