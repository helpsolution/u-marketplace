package com.reljicd.service;

import com.reljicd.dto.ProductDto;
import com.reljicd.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    Page<Product> findAllProductsPageable(Pageable pageable);

    Product addProduct(ProductDto productDto);

    void deleteProduct(Product product);

    Collection<Product> findAllProductForSeller(String username);



    void updateProduct(ProductDto productDto);


}
