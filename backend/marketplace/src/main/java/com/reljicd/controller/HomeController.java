package com.reljicd.controller;

import com.reljicd.model.Product;
import com.reljicd.model.Role;
import com.reljicd.model.User;
import com.reljicd.service.ProductService;
import com.reljicd.service.UserService;
import com.reljicd.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

@Controller
public class HomeController {

    private static final int INITIAL_PAGE = 0;

    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public HomeController(ProductService productService,
                          UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("*")
    public Object home(@RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        if(!page.isPresent() && name != null) {
            Optional<User> user = userService.findByUsername(name);
            Collection<Role> roles = user.get().getRoles();
            Role mainRole = roles.iterator().next();
            if (mainRole.getRole().equals("ROLE_ANALYST")) {
                return "redirect:/analytic-reports";
            }
            if(mainRole.getRole().equals("ROLE_CUSTOMER")) {
                return "redirect:/shoppingCart";
            }
            if(mainRole.getRole().equals("ROLE_SELLER")) {
                return "redirect:/sellerCab";
            }
        }


        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> products = productService.findAllProductsPageable(new PageRequest(evalPage, 5));
        Pager pager = new Pager(products);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", products);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/home");
        return modelAndView;
    }

}
