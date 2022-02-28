package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.model.Role;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repos.RoleRepo;
import com.example.servingwebcontent.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;


@Controller
public class GreetingController {


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    RoleRepo roleRepository;


    @GetMapping("/")
    public String greeting() {
        return "redirect:/login";
    }

    @GetMapping(value = "/admin")
    public String showUsers(ModelMap model) {
        Iterable<User> allUsers = userService.allUsers();
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allRoles", roles);
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @GetMapping("/user")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}

