package com.softwork.softwork.service.impl;


import com.softwork.softwork.config.JwtTokenProvider;
import com.softwork.softwork.config.UserDetailImpl;
import com.softwork.softwork.config.UserDetailsServiceImpl;
import com.softwork.softwork.exception.AppException;
import com.softwork.softwork.models.*;
import com.softwork.softwork.payload.*;
import com.softwork.softwork.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("jpa")
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final RoleRepository roleRepository;
    private final RoleCategoryRepository roleCategoryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserDetailsServiceImpl userDetailsService, RoleRepository roleRepository, RoleCategoryRepository roleCategoryRepository) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
        this.roleCategoryRepository = roleCategoryRepository;
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest)throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest
                            .getUsernameOrEmail(), loginRequest.getPassword())
            );
            Optional<User> user = userRepository.findByEmail(loginRequest.getUsernameOrEmail());
            if(user.isEmpty()){
                User user1 = userRepository.findByUsername(loginRequest.getUsernameOrEmail()).get();
                final String jwt = tokenProvider.generateToken(user1.getUsername());
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            }

//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(loginRequest.getUsernameOrEmail());

            final String jwt = tokenProvider.generateToken(user.get().getEmail());
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }
        catch (BadCredentialsException e) {
            throw new AppException("Incorrect username or password", e);
        }

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<?> createUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false,
                    "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false,
                    "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getFirstname(), signUpRequest.getLastname(),
                signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(
                true, "User registered successfully"));
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<?> getUser(Optional<Long> userId , Optional<Long> roleId, Optional<Long>
            roleCategoryId) {
        if( userId.isEmpty() && roleId.isEmpty() && roleCategoryId.isEmpty()) {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        if(userId.isEmpty() && roleCategoryId.isEmpty()){
            Optional<Role> role = roleRepository.findById(roleId.get());
            List<User> users = userRepository.findAllByRoles(role.get());
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        if(userId.isEmpty() && roleId.isEmpty()){
            Optional<RoleCategory> roleCategory = roleCategoryRepository.findById(roleCategoryId.get());
            List<User> users = userRepository.findAllByRoleCategory(roleCategory.get());
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        Optional<User> user   = userRepository.findById(userId.get());
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity(new ApiResponse(false, "Specified user is not available!"),
                HttpStatus.BAD_REQUEST));
    }




}
