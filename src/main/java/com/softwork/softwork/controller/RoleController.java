package com.softwork.softwork.controller;

import com.softwork.softwork.payload.CategoryRequest;
import com.softwork.softwork.service.RoleService;
import com.softwork.softwork.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class RoleController {

    RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }



    @PostMapping(value = "/add")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return roleService.addRoleCategory(categoryRequest);
    }

    @PostMapping(value = "/update/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable(value="id", required = true) Long catId) {
        return roleService.updateRoleCategory(categoryRequest, catId);
    }

    @GetMapping(value = {"/get", "/get/{id}"})
    public ResponseEntity<?> getCategory(@PathVariable(value="id",required=false) Optional<Integer> id) {
        return roleService.getRoleCategory(id);
    }
}
