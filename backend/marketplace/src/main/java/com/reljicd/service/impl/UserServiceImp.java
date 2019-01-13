package com.reljicd.service.impl;

import com.reljicd.dto.AnalystDTO;
import com.reljicd.dto.SellerDTO;
import com.reljicd.model.Analyst;
import com.reljicd.model.User;
import com.reljicd.repository.AnalystRepository;
import com.reljicd.repository.RoleRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AnalystRepository analystRepository;

    private static final String ROLE_SELLER = "ROLE_SELLER";

    @Autowired
    public UserServiceImp(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AnalystRepository analystRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.analystRepository = analystRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        // Encode plaintext password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setActive(1);
        // Set Role to ROLE_USER
        user.setRoles(Collections.singletonList(roleRepository.findByRole(ROLE_SELLER)));
        return userRepository.save(user);
    }

    @Override
    public User saveSeller(SellerDTO sellerDTO) {

        String encodedPassword = passwordEncoder.encode(sellerDTO.getPassword());
        User user = new User(
         null, sellerDTO.getUsername(),
         encodedPassword, sellerDTO.getEmail(), sellerDTO.getName(),
         sellerDTO.getPhone(),
         Collections.singletonList(roleRepository.findByRole(ROLE_SELLER))
        );
        return userRepository.save(user);
    }

    @Override
    public User saveAnalyst(AnalystDTO analystDTO) {

        String encodedPassword = passwordEncoder.encode(analystDTO.getPassword());
        User user = new User(
         null, analystDTO.getUsername(),
         encodedPassword, analystDTO.getEmail(), analystDTO.getName(),
         analystDTO.getPhone(),
         Collections.singletonList(roleRepository.findByRole(ROLE_SELLER))
        );
        User savedUser = userRepository.save(user);
        Analyst analyst = new Analyst(savedUser.getId(), analystDTO.getSpecialization());
        analystRepository.save(analyst);
        return savedUser;
    }
}
