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

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) throws Exception {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping(value = "admin/edit")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/new")
    public String newUser(ModelMap model){
        model.addAttribute("newUser", new User());
        return "editPage";
    }

    @PostMapping(value = "admin/add")
    public String add(@ModelAttribute("user") User user,
                        @RequestParam("authorities") List<String> values ) {
        Set<Role> roleSet = userService.getSetOfRoles(values);
        user.setRoles(roleSet);
        userService.add(user);
        return "redirect:/admin";

    }

    @GetMapping(value = "admin/delete/{id}")
    public String remove(@PathVariable("id") int id) throws Exception {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }


}

