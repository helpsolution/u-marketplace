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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Anna Guminskaya
 * @since 15/01/2019.
 */
@Controller
public class ProductListController {

    private static final int INITIAL_PAGE = 0;

    @Autowired
    private ProductService productService;

    @GetMapping("/product-list")
    public ModelAndView productList(@RequestParam("page") Optional<Integer> page) {

        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> products = productService.findAllProductsPageable(new PageRequest(evalPage, 5));
        Pager pager = new Pager(products);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product-list");
        modelAndView.addObject("products", products);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }

}
