package com.softwork.softwork.service.impl;

import com.softwork.softwork.config.JwtTokenProvider;
import com.softwork.softwork.config.UserDetailImpl;
import com.softwork.softwork.exception.AppException;
import com.softwork.softwork.models.RoleCategory;
import com.softwork.softwork.models.SkillCategory;
import com.softwork.softwork.models.User;
import com.softwork.softwork.payload.ApiResponse;
import com.softwork.softwork.payload.CategoryRequest;
import com.softwork.softwork.repository.SkillCategoryRepository;
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
public class SkillServiceImpl {


    @Autowired
    SkillCategoryRepository skillCategoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    /**
     * @param categoryRequest, skill name  e.g HTML 5
     * @return success message
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResponseEntity<?> addSkillCategory(CategoryRequest categoryRequest) {

        if (skillCategoryRepository.existsByName(categoryRequest.getName())) {
            return new ResponseEntity(
                    new ApiResponse(false, "Already there is category named " + categoryRequest.getName()),
                    HttpStatus.BAD_REQUEST);
        }

        SkillCategory result = skillCategoryRepository
                .save(new SkillCategory(categoryRequest.getName()));

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/skill-category/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Category has been added successfully!"));

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResponseEntity<?> updateSkillCategory(CategoryRequest categoryRequest, Long catId) {
        SkillCategory skillCategory = skillCategoryRepository.findById(catId).orElse(null);
        if (skillCategory != null) {
            skillCategory.setName(categoryRequest.getName());
            SkillCategory result = skillCategoryRepository.save(skillCategory);
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/skill-category/{id}")
                    .buildAndExpand(result.getId()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Category has been updated successfully!"));
        }

        return new ResponseEntity(
                new ApiResponse(false, "There is no such  Category found!" + catId),
                HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResponseEntity<?> getSkillCategory(Optional<Integer> categoryId) {

        if(categoryId.isEmpty()) {
            List<SkillCategory> categories = skillCategoryRepository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        Optional<SkillCategory> category   = skillCategoryRepository.findById(Long.valueOf(categoryId.get()));
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity(new ApiResponse(false, "Specified category is not available!"),
                HttpStatus.BAD_REQUEST));

    }

    public ResponseEntity<?> selectSkillCategory(Set<Long> catIds, HttpServletRequest request) {
        Optional<User> user = jwtTokenProvider.resolveUser(request);
        System.out.println(user.get().getUsername());
        if(user.isEmpty()) throw new AppException("User does not exist");
        Set<SkillCategory> skillCategories = catIds.stream()
                .map(id ->skillCategoryRepository.findById(id).get()).collect(Collectors.toSet());
        skillCategories.addAll(user.get().getSkillCategory());
        user.get().setSkillCategory(skillCategories);
        userRepository.save(user.get());
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.get().getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(
                true, "Role category add successfully"));
    }
}
