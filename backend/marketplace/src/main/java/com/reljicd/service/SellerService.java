package com.reljicd.service;

import com.reljicd.model.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    Optional<Seller> findById(Long id);
    Optional<Seller> findByUsername(String name);
}
