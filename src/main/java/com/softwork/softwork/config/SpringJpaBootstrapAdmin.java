package com.softwork.softwork.config;


import com.softwork.softwork.models.RoleEnum;
import com.softwork.softwork.models.Role;
import com.softwork.softwork.models.User;
import com.softwork.softwork.repository.RoleRepository;
import com.softwork.softwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Adds the first
 * user as admin
 * to database
 */


//@Configuration
@Component
public class SpringJpaBootstrapAdmin implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringJpaBootstrapAdmin(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadFirstUserAsAdmin();
    }

    private void loadFirstUserAsAdmin() {
        Optional<Role> role = roleRepository.findByRoleEnum(RoleEnum.ADMIN);
        if (role.isEmpty()) {
            User superAdmin = new User();
            superAdmin.setFirstname("superAdmin");
            superAdmin.setLastname("superAdmin");
            superAdmin.setUsername("admin");
            superAdmin.setPassword(passwordEncoder.encode("admin"));
            superAdmin.setEmail("super@Admin");

            Role role1 = new Role();
            Role role2 = new Role();
            Role role3 = new Role();

            role1.setRoleEnum(RoleEnum.ADMIN);
            role2.setRoleEnum(RoleEnum.RECRUITER);
            role3.setRoleEnum(RoleEnum.USER);
            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);

            superAdmin.setRoles(Set.of(role1, role2, role3));
            userRepository.save(superAdmin);
        }
    }
}
