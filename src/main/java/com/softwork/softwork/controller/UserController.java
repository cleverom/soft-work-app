package com.softwork.softwork.controller;

import com.softwork.softwork.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/get", "/get/{id}", "/get-by-role/{role-id}",
            "/get-by-role-category/{role-category-id}"})
    public ResponseEntity<?> getCategory(@PathVariable(value="id", required=false) Optional<Long> id,
    @PathVariable(value="role-id",required = false) Optional<Long> roleId, @PathVariable(value="role-category-id",
            required = false) Optional<Long> roleCategoryId) {
        return userService.getUser(id, roleId, roleCategoryId);
    }
}
