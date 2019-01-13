package com.reljicd.service.impl;

import com.reljicd.model.Seller;
import com.reljicd.repository.SellerRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    @Override
    public Seller findById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public Seller findByUsername(String name) {
        return sellerRepository.findByUsername(name);
    }
}
