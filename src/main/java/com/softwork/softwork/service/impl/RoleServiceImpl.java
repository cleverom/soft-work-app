package com.softwork.softwork.service.impl;

import com.softwork.softwork.config.JwtTokenProvider;
import com.softwork.softwork.config.UserDetailImpl;
import com.softwork.softwork.exception.AppException;
import com.softwork.softwork.models.RoleCategory;
import com.softwork.softwork.models.User;
import com.softwork.softwork.payload.ApiResponse;
import com.softwork.softwork.payload.CategoryRequest;
import com.softwork.softwork.repository.RoleCategoryRepository;
import com.softwork.softwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl {

    @Autowired
    RoleCategoryRepository roleCategoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

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

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/role-category/{id}")
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
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/role-category/{id}")
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

    public ResponseEntity<?> selectRoleCategory(Set<Long> catIds, HttpServletRequest request) {
        Optional<User> user = jwtTokenProvider.resolveUser(request);
        System.out.println(user.get().getUsername());
        if(user.isEmpty()) throw new AppException("User does not exist");
        Set<RoleCategory> roleCategories = catIds.stream()
                .map(id ->roleCategoryRepository.findById(id).get()).collect(Collectors.toSet());
        roleCategories.addAll(user.get().getRoleCategory());
        user.get().setRoleCategory(roleCategories);
        userRepository.save(user.get());
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.get().getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(
                true, "Role category add successfully"));
    }

}
