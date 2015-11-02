package com.bestmarket.controller;

import com.bestmarket.service.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private ShoppingCart shoppingCart;

    @RequestMapping(method= RequestMethod.GET)
    public String logoutUser (){
        shoppingCart.clear();
        return "redirect: /j_spring_security_logout";
    }
}
