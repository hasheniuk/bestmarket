package com.bestmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/about")
public class AboutController {

    @RequestMapping(method = RequestMethod.GET)
    public String about(){
        return "about";
    }

}
