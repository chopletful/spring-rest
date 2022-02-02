package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repos.UserRepo;
//import com.example.servingwebcontent.service.UserService;
import com.example.servingwebcontent.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class GreetingController {


//    @Autowired
//    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/")
    public String greeting() {
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String showUsers(ModelMap model) {
        Iterable<User> allUsers = userService.allUsers();
        model.addAttribute("allUsers", allUsers);
        return "user";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) throws Exception {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model){
        model.addAttribute("user", new User());
        return "editPage";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";

    }

    @GetMapping(value = "/delete/{id}")
    public String remove(@PathVariable("id") int id) throws Exception {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/users";
    }


}

