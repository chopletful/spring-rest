package com.example.servingwebcontent.repos;

import com.example.servingwebcontent.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
}
