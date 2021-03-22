package com.softwork.softwork.service.impl;

import com.softwork.softwork.models.Applicant;
import com.softwork.softwork.models.RoleCategory;
import com.softwork.softwork.payload.ApiResponse;
import com.softwork.softwork.payload.CategoryRequest;
import com.softwork.softwork.repository.ApplicantRepository;
import com.softwork.softwork.repository.RoleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.expression.Sets;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl {

    @Autowired
    RoleCategoryRepository roleCategoryRepository;
    @Autowired
    ApplicantRepository applicantRepository;

    /**
     * @param categoryRequest, role name e.g Software Engineer
     * @return success message
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResponseEntity<?> addRoleCategory(CategoryRequest categoryRequest) {

        if (roleCategoryRepository.existsByName(categoryRequest.getName())) {
            return new ResponseEntity(
                    new ApiResponse(false, "Already there is category named " + categoryRequest.getName()),
                    HttpStatus.BAD_REQUEST);
        }

        RoleCategory result = roleCategoryRepository
                .save(new RoleCategory(categoryRequest.getName(), categoryRequest.getDescription()));

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/category/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "RoleCategory has been added successfully!"));

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResponseEntity<?> updateRoleCategory(CategoryRequest categoryRequest, Long catId) {
        RoleCategory roleCategory = roleCategoryRepository.findById(catId).orElse(null);
        if (roleCategory != null) {
            roleCategory.setName(categoryRequest.getName());
            roleCategory.setDescription(categoryRequest.getDescription());
            RoleCategory result = roleCategoryRepository.save(roleCategory);
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/roleCategory/{id}")
                    .buildAndExpand(result.getId()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "RoleCategory has been updated successfully!"));
        }

        return new ResponseEntity(
                new ApiResponse(false, "There is no such  roleCategory found!" + catId),
                HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResponseEntity<?> getRoleCategory(Optional<Integer> categoryId) {

        if(categoryId.isEmpty()) {
            List<RoleCategory> categories = roleCategoryRepository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        Optional<RoleCategory> category   = roleCategoryRepository.findById(Long.valueOf(categoryId.get()));
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity(new ApiResponse(false, "Specified category is not available!"),
                HttpStatus.BAD_REQUEST));
    }

//    public ResponseEntity<?> addRole(Set<RoleCategory> roles, UserPrincipal currentUser){
//        Applicant applicant = applicantRepository.findByEmail(currentUser.getEmail());
//        applicant.setRoleCategory(roles);
//    }

}
