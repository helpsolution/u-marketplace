package com.reljicd.service.impl;

import com.reljicd.model.Product;
import com.reljicd.repository.ProductRepository;
import com.reljicd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Collection<Product> findAllProductForSeller(String username) {
        return productRepository.findAllForSeller(username);
    }

//    @Override
//    public void updateProduct(Product product) {
//        productRepository.update();
//    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
