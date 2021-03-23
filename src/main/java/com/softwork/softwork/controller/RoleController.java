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



    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return roleService.addRoleCategory(categoryRequest);
    }

    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable(value="id", required = true) Long catId) {
        return roleService.updateRoleCategory(categoryRequest, catId);
    }

    @GetMapping(value = {"/get", "/get/{id}"})
    public ResponseEntity<?> getCategory(@PathVariable(value="id",required=false) Optional<Integer> id) {
        return roleService.getRoleCategory(id);
    }

    @PostMapping(value = {"/select-role/{id}"})
    public ResponseEntity<?> selectRoleCategory(@PathVariable(value="id",required=false) Set<Long> ids,
                                                HttpServletRequest request ){
        return roleService.selectRoleCategory(ids, request);
    }
}
