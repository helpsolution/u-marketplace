package com.reljicd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reljicd.dto.CustomerDTO;
import com.reljicd.service.UserService;

@Controller
public class CustomerRegistrationController {

    private final UserService userService;

    @Autowired
    public CustomerRegistrationController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/registration-customer", method = RequestMethod.GET)
    public ModelAndView registrationCustomer() {
        ModelAndView modelAndView = new ModelAndView();
        CustomerDTO customerDTO = new CustomerDTO();
        modelAndView.addObject("customerDTO", customerDTO);
        modelAndView.setViewName("/registration-customer");
        return modelAndView;
    }

    @RequestMapping(value = "/registration-customer", method = RequestMethod.POST)
    public ModelAndView createNewCustomer(@Valid CustomerDTO customerDTO, BindingResult bindingResult) {

        if (userService.findByEmail(customerDTO.getEmail()).isPresent()) {
            bindingResult
             .rejectValue("email", "error.customerDTO",
                          "There is already a user registered with the email provided");
        }
        if (userService.findByUsername(customerDTO.getUsername()).isPresent()) {
            bindingResult
             .rejectValue("username", "error.customerDTO",
                          "There is already a user registered with the username provided");
        }

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/registration-customer");
        } else {
            // Registration successful, save user
            // Set user role to USER and set it as active
            userService.saveCustomer(customerDTO);

            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("customerDTO", new CustomerDTO());
            modelAndView.setViewName("/registration-customer");
        }
        return modelAndView;

    }
}
