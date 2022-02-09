package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class GreetingController {


    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/")
    public String greeting() {
        return "redirect:/login";
    }

    @GetMapping(value = "/admin")
    public String showUsers(ModelMap model) {
        Iterable<User> allUsers = userService.allUsers();
        model.addAttribute("allUsers", allUsers);
        return "user";
    }

    @GetMapping("/user")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("currUser");
        modelAndView.addObject("user", user);
        return modelAndView;
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
        return "redirect:/admin";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model){
        model.addAttribute("user", new User());
        return "editPage";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";

    }

    @GetMapping(value = "/delete/{id}")
    public String remove(@PathVariable("id") int id) throws Exception {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }


}

