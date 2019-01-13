package com.reljicd.service.impl;

import com.reljicd.dto.ProductDto;
import com.reljicd.model.Product;
import com.reljicd.repository.CategoryRepository;
import com.reljicd.repository.ProductRepository;
import com.reljicd.repository.SellerRepository;
import com.reljicd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uname = auth.getName(); //get logged in username
        Product product = new Product(sellerRepository.findByUsername(uname), categoryRepository.findById(Long.parseLong(productDto.getCategoryId())), productDto.getName(), productDto.getDescription(), new BigDecimal(productDto.getPrice()), productDto.getColor(), productDto.getSize(), Integer.parseInt(productDto.getQuantity()));
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
