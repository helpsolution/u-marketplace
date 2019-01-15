package com.reljicd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reljicd.dto.AnalystDTO;
import com.reljicd.service.UserService;

@Controller
public class AnalystRegistrationController {

    private final UserService userService;

    @Autowired
    public AnalystRegistrationController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/registration-analyst", method = RequestMethod.GET)
    public ModelAndView registrationAnalyst() {
        ModelAndView modelAndView = new ModelAndView();
        AnalystDTO analystDTO = new AnalystDTO();
        modelAndView.addObject("analystDTO", analystDTO);
        modelAndView.setViewName("/registration-analyst");
        return modelAndView;
    }

    @RequestMapping(value = "/registration-analyst", method = RequestMethod.POST)
    public ModelAndView createNewAnalyst(@Valid AnalystDTO analystDTO, BindingResult bindingResult) {

        if (userService.findByEmail(analystDTO.getEmail()).isPresent()) {
            bindingResult
             .rejectValue("email", "error.analystDTO",
                          "Пользователь с  таким e-mail уже существует");
        }
        if (userService.findByUsername(analystDTO.getUsername()).isPresent()) {
            bindingResult
             .rejectValue("username", "error.analystDTO",
                          "Пользователь с таким именем уже зарегистрирован");
        }

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/registration-analyst");
        } else {
            // Registration successful, save user
            // Set user role to USER and set it as active
            userService.saveAnalyst(analystDTO);

            modelAndView.addObject("successMessage", "Пользователь успешно зарегистрирован");
            modelAndView.addObject("analystDTO", new AnalystDTO());
            modelAndView.setViewName("/registration-analyst");
        }
        return modelAndView;

    }
}
