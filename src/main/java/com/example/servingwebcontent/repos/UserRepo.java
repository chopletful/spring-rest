package com.example.servingwebcontent.repos;

import com.example.servingwebcontent.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepo
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
