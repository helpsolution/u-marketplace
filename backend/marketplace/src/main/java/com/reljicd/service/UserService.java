package com.reljicd.service;

import com.reljicd.dto.AnalystDTO;
import com.reljicd.dto.SellerDTO;
import com.reljicd.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User saveUser(User user);

    User saveSeller(SellerDTO sellerDTO);

    User saveAnalyst(AnalystDTO analystDTO);

}
