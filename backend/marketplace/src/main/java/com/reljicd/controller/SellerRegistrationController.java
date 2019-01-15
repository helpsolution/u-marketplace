package com.reljicd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reljicd.dto.SellerDTO;
import com.reljicd.model.Category;
import com.reljicd.model.Company;
import com.reljicd.repository.CompanyRepository;
import com.reljicd.service.UserService;

@Controller
public class SellerRegistrationController {

    private final UserService userService;

    private final CompanyRepository companyRepository;

    @Autowired
    public SellerRegistrationController(UserService userService,
                                        CompanyRepository companyRepository) {
        this.userService = userService;
        this.companyRepository = companyRepository;
    }

    @ModelAttribute("allCompanies")
    public List<Company> categories() {
        return companyRepository.findAll();
    }
    
    @RequestMapping(value = "/registration-seller", method = RequestMethod.GET)
    public ModelAndView registrationSeller() {
        ModelAndView modelAndView = new ModelAndView();
        SellerDTO sellerDTO = new SellerDTO();
        modelAndView.addObject("sellerDTO", sellerDTO);
        modelAndView.setViewName("/registration-seller");
        return modelAndView;
    }

    @RequestMapping(value = "/registration-seller", method = RequestMethod.POST)
    public ModelAndView createNewSeller(@Valid SellerDTO sellerDTO, BindingResult bindingResult) {

        if (userService.findByEmail(sellerDTO.getEmail()).isPresent()) {
            bindingResult
             .rejectValue("email", "error.sellerDTO",
                          "There is already a user registered with the email provided");
        }
        if (userService.findByUsername(sellerDTO.getUsername()).isPresent()) {
            bindingResult
             .rejectValue("username", "error.sellerDTO",
                          "There is already a user registered with the username provided");
        }

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/registration-seller");
        } else {
            // Registration successful, save user
            // Set user role to USER and set it as active
            userService.saveSeller(sellerDTO);

            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("sellerDTO", new SellerDTO());
            modelAndView.setViewName("/registration-seller");
        }
        return modelAndView;

    }
}
