package com.softwork.softwork.service;

import com.softwork.softwork.payload.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {
    public ResponseEntity<?> addRoleCategory(CategoryRequest categoryRequest);
    public ResponseEntity<?> updateRoleCategory(CategoryRequest categoryRequest, Long catId);
    public ResponseEntity<?> getRoleCategory(Optional<Integer> categoryId);
}
