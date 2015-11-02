package com.bestmarket.controller;

import com.bestmarket.entity.*;
import com.bestmarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCart shoppingCart;

    @RequestMapping(method = RequestMethod.GET)
    public String order(ModelMap modelMap, Principal principal){
        User user = userService.findByMail(principal.getName());
        shoppingCart.setUser(user);

        List<ProductOutcome> boughtProducts = shoppingCart.order();
        modelMap.addAttribute("boughtProducts", boughtProducts);
        modelMap.addAttribute("total", shoppingCart.getTotalPrice());
        shoppingCart.clear();
        return "order";
    }
}
