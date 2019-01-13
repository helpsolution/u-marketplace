package com.reljicd.controller;

import com.reljicd.dto.ProductDto;
import com.reljicd.model.Category;
import com.reljicd.service.CategoryService;
import com.reljicd.service.ProductService;
import com.reljicd.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductAddController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SellerService sellerService;

    @Autowired
    public ProductAddController(ProductService productService, CategoryService categoryService,SellerService sellerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.sellerService = sellerService;
    }

    @RequestMapping(value = "/sellerCabAdd", method = RequestMethod.GET)
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView();
        ProductDto productDto = new ProductDto();
        modelAndView.addObject("productDto", productDto);
        modelAndView.setViewName("/sellerCabAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/sellerCabAdd", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid ProductDto productDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uname = auth.getName(); //get logged in username

        ModelAndView modelAndView = new ModelAndView();

        productService.addProduct(productDto);
        modelAndView.addObject("successMessage", "Товар успешно добавлен");
        modelAndView.addObject("productDto", new ProductDto());
        modelAndView.setViewName("/sellerCabAdd");

        return modelAndView;
    }

    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAllCategory();
    }

}
