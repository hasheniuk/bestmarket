package com.bestmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method=RequestMethod.GET)
    public String loginUser (@RequestParam(value = "error", required = false) String error, Model model, Principal principal){
        if (error != null) {
            model.addAttribute("error", "Invalid e-mail or password!");
        }
        return "login";
    }
}
