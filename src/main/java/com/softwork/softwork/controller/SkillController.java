package com.softwork.softwork.controller;

import com.softwork.softwork.payload.CategoryRequest;
import com.softwork.softwork.service.impl.RoleServiceImpl;
import com.softwork.softwork.service.impl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/skill")
public class SkillController {

    SkillServiceImpl skillService;

    @Autowired
    public SkillController(SkillServiceImpl skillService) {
        this.skillService = skillService;
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/add")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return skillService.addSkillCategory(categoryRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/update/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable(value="id", required = true) Long catId) {
        return skillService.updateSkillCategory(categoryRequest, catId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = {"/get", "/get/{id}"})
    public ResponseEntity<?> getCategory(@PathVariable(value="id",required=false) Optional<Integer> id) {
        return skillService.getSkillCategory(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = {"/select-skill/{id}"})
    public ResponseEntity<?> selectRoleCategory(@PathVariable(value="id",required=false) Set<Long> ids,
                                                HttpServletRequest request ){
        return skillService.selectSkillCategory(ids, request);
    }

}
