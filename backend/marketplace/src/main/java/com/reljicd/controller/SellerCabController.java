package com.reljicd.controller;


import com.reljicd.dto.ProductDto;
import com.reljicd.service.CategoryService;
import com.reljicd.service.ProductService;
import com.reljicd.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SellerCabController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SellerService sellerService;

    @Autowired
    public SellerCabController(ProductService productService, CategoryService categoryService,SellerService sellerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.sellerService = sellerService;
    }



    @GetMapping("/sellerCab")
    public ModelAndView sellerCab() {
        ModelAndView modelAndView = new ModelAndView("/sellerCab");
//        Product product = new Product();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        modelAndView.addObject("products", productService.findAllProductForSeller(name));
        return modelAndView;
    }

    @GetMapping("/sellerCab/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productService.findById(productId).get());
        return sellerCab();
    }

}
