package com.reljicd.controller;

import com.reljicd.dto.OrderDTO;
import com.reljicd.dto.PayingDTO;
import com.reljicd.service.ProductService;
import com.reljicd.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

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



//    @GetMapping("/shoppingCart/checkout")
//    public ModelAndView checkout() {
//        ModelAndView modelAndView = new ModelAndView();
//        OrderDTO orderDTO = new OrderDTO();
//        modelAndView.addObject("orderDTO", orderDTO);
//        modelAndView.addObject("basketId", "12");
//
//        modelAndView.setViewName("/delivery");
//        return modelAndView;
//    }

    @RequestMapping(value = "/shoppingCart/checkout", method = RequestMethod.POST)
    public ModelAndView checkout( OrderDTO orderDTO, BindingResult bindingResult) {

        ModelAndView modelAndView = shoppingCart();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/shoppingCart");
        } else {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();

            shoppingCartService.checkout(name, orderDTO.getAddress(), orderDTO.getPayingType());

            modelAndView.addObject("successMessage", "Заявка оформлена успешно");
            modelAndView.addObject("orderDTO", new OrderDTO());
            modelAndView.setViewName("/shoppingCart");
        }
        return modelAndView;

    }

//    @GetMapping("/shoppingCart/payment")
//    public ModelAndView paymant() {
//        ModelAndView modelAndView = new ModelAndView();
//        PayingDTO payingDTO = new PayingDTO();
//        modelAndView.addObject("payingDTO", payingDTO);
//        modelAndView.setViewName("/payment");
//        return modelAndView;
//    }
}
