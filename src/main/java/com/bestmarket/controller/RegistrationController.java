package com.bestmarket.controller;

import com.bestmarket.entity.*;
import com.bestmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET)
    public String openRegistrationForm(Model model){
        model.addAttribute(new User());
        return "registration";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String registerUser(User user, ModelMap modelMap) {
        try {
            user.setRole(Role.USER);
            userService.saveAnfFlush(user);
            return "redirect:/login";
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            modelMap.addAttribute("registered", "User with e-mail " + user.getEmail() + " is already registered");
            return "registration";
        }
    }
}