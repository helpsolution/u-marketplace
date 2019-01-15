package com.reljicd.controller;


import com.reljicd.dto.ProductDto;
import com.reljicd.model.Category;
import com.reljicd.model.Product;
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
import java.util.List;


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


    @RequestMapping(value = "/sellerCab/editProduct/{productId}", method = RequestMethod.GET)
    public ModelAndView editProductFromCart(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("/editProduct");
        Product product = productService.findById(productId).get();
        ProductDto productDto = new ProductDto(productId,product.getCategory().getId().toString(), product.getName(), product.getDescription(), product.getPrice().toString(), product.getColor(), product.getSize(), product.getQuantity().toString());
        modelAndView.addObject("productDto", productDto);
        return modelAndView;
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public ModelAndView editProductFromCart(@Valid ProductDto productDto) {
        ModelAndView modelAndView = new ModelAndView("/editProduct");
        productService.updateProduct(productDto);
        return modelAndView;
    }



    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAllCategory();
    }
}
