package com.reljicd.service;

import com.reljicd.model.Seller;



public interface SellerService {
    Seller findById(Long id);
    Seller findByUsername(String name);
}
