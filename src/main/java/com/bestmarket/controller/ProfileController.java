package com.bestmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.bestmarket.entity.*;
import com.bestmarket.service.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(method= RequestMethod.GET)
    public String openRegistrationForm(Model model, Principal principal, ModelMap modelMap){
        model.addAttribute(new User());
        user = userService.findByMail(principal.getName());
        modelMap.addAttribute("user", user);
        List<Order> orderList = orderService.findByUser(user);
        modelMap.addAttribute("orders", orderList);
        return "profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateUser(User updatedUser){
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhone(updatedUser.getPhone());
        user.setPassword(updatedUser.getPassword());
        userService.saveAnfFlush(user);
        return "profile";
    }
}
