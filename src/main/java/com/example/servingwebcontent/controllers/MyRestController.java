package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class MyRestController {

    @Autowired
    UserService users;

    @GetMapping("/allUsers")
    List<User> getUser() throws Exception {
        List<User> user = (List<User>) users.allUsers();
        return user;
    }

    @GetMapping("/{id}")
    User getById(@PathVariable int id) throws Exception {
        User user = users.getById(id);
        return user;
    }

    @PostMapping("/addnew")
    User addUser(@RequestBody User user){
        users.add(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    User delete(@PathVariable int id) throws Exception {
        User user = users.getById(id);
        users.delete(user);
        return user;
    }

    @DeleteMapping("/delete")
    User del(@RequestParam int id)throws Exception {
        User user = users.getById(id);
        users.delete(user);
        return user;
    }

    @GetMapping("/edit")
    public User getEditForm(@RequestParam int id) throws Exception {
        return users.getById(id);
    }


//    {
//        "age": "27",
//            "name": "Ivan",
//            "lastname": "Fedotov",
//            "password": "a",
//            "login": "a",
//            "passwordConfirm": null,
//            "roles": [
//        {
//            "id": 1,
//                "name": "ROLE_ADMIN",
//                "users": [
//
//      ],
//            "authority": "ROLE_ADMIN"
//        }
//  ]
//    }
}

