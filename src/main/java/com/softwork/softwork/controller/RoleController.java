package com.softwork.softwork.controller;

import com.softwork.softwork.config.UserDetailImpl;
import com.softwork.softwork.payload.CategoryRequest;
import com.softwork.softwork.service.RoleService;
import com.softwork.softwork.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/category")
public class RoleController {

    RoleServiceImpl roleService;
//    UserDetailImpl userDetail;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return roleService.addRoleCategory(categoryRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable(value="id", required = true) Long catId) {
        return roleService.updateRoleCategory(categoryRequest, catId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = {"/get", "/get/{id}"})
    public ResponseEntity<?> getCategory(@PathVariable(value="id",required=false) Optional<Integer> id) {
        return roleService.getRoleCategory(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = {"/select-role/{id}"})
    public ResponseEntity<?> selectRoleCategory(@PathVariable(value="id",required=false) Set<Long> ids,
                                                HttpServletRequest request ){
        return roleService.selectRoleCategory(ids, request);
    }
}
