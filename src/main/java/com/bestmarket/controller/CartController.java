package com.bestmarket.controller;

import com.bestmarket.service.*;
import com.bestmarket.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String cart(ModelMap modelMap){
        Map<Product, Integer> products = shoppingCart.getProducts();
        modelMap.addAttribute("products", products);
        BigDecimal totalPrice = shoppingCart.getTotalPrice();
        modelMap.addAttribute("total", totalPrice);
        if(totalPrice.longValue() == 0){
            modelMap.addAttribute("deactivate", "disabled='true'");
        }
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String changeProductData(@RequestBody final String param){
        // 0 - productName, 1 - cart product count
        String[] parameters = param.split("\\&");
        String name = parameters[0].split("=")[1].replace("+", " ");
        Product product = productService.findByName(name);
        int count = Integer.parseInt(parameters[1].split("=")[1]);
        if (count > 0) {
            shoppingCart.add(product);
        } else {
            shoppingCart.remove(product);
        }
        return "redirect:/cart";
    }
}
