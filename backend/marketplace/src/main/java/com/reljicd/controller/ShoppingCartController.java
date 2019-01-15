package com.reljicd.controller;

import com.reljicd.dto.OrderDTO;
import com.reljicd.model.Category;
import com.reljicd.service.CategoryService;
import com.reljicd.service.ProductService;
import com.reljicd.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        OrderDTO orderDTO = new OrderDTO();
        modelAndView.addObject("orderDTO", orderDTO);
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

//    @GetMapping("/shoppingCart/checkout")
//    public ModelAndView checkout() {
//        try {
//            shoppingCartService.checkout();
//        } catch (NotEnoughProductsInStockException e) {
//            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
//        }
//        return shoppingCart();
//    }


    @GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        ModelAndView modelAndView = new ModelAndView();
        OrderDTO orderDTO = new OrderDTO();
        modelAndView.addObject("orderDTO", orderDTO);
        modelAndView.setViewName("/shoppingCart/checkout");
        return modelAndView;
    }

    @PostMapping(value = "/shoppingCart/checkout")
    public ModelAndView checkout(@Valid OrderDTO orderDTO, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("products", shoppingCartService.getProductsInCart());
            modelAndView.addObject("total", shoppingCartService.getTotal().toString());
            modelAndView.setViewName("/shoppingCart");
        } else {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();

            try {
                shoppingCartService.checkout(name, orderDTO.getAddress(), orderDTO.getPayingType(), orderDTO.getDateFrom(), orderDTO.getDateTo());
                modelAndView.addObject("successMessage", "Заявка оформлена успешно");
            } catch (Exception e) {
                modelAndView.addObject("businessError", e.toString());
            }
            modelAndView.addObject("orderDTO", new OrderDTO());
            modelAndView.addObject("products", shoppingCartService.getProductsInCart());
            modelAndView.addObject("total", shoppingCartService.getTotal().toString());
            modelAndView.setViewName("/shoppingCart");

        }
        return modelAndView;

    }
}
