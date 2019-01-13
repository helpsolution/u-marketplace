package com.reljicd.controller;

import com.reljicd.exception.NotEnoughProductsInStockException;
import com.reljicd.service.CategoryService;
import com.reljicd.service.ProductService;
import com.reljicd.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SellerCabController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public SellerCabController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/sellerCab")
    public ModelAndView sellerCab() {
        ModelAndView modelAndView = new ModelAndView("/sellerCab");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        modelAndView.addObject("products", productService.findAllProductForSeller(name));
        return modelAndView;
    }

//    @GetMapping("/sellerCab/addProduct")
//    public ModelAndView addProduct(@PathVariable("productId") Long productId) {
//        productService.addProduct();
//        return sellerCab();
//    }
//
//    @GetMapping("/sellerCab/removeProduct/{productId}")
//    public ModelAndView removeProduct(@PathVariable("productId") Long productId) {
//        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
//        return sellerCab();
//    }

}
