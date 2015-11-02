package com.bestmarket.controller;

import com.bestmarket.entity.*;
import com.bestmarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/category/All", "/", "/index"})
public class IndexController {

    private static final int PRODUCTS_PER_PAGE = 8;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCart shoppingCart;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String redirect(){
        return "redirect:/category/all/page/1";
    }

    @RequestMapping(value = "/category/{categoryName}/page/{number}", method = RequestMethod.GET)
    public String page(@PathVariable String categoryName, @PathVariable String number, ModelMap modelMap) {

        // Render categories of the products on the sidebar
        modelMap.addAttribute("categories", Category.values());

        // Select products depend on the category
        List<Product> productList = getProductListByCategory(Category.of(categoryName));

        // Number of current page
        int pageNumber = validatePageNumber(number);

        return switchPageByPageNumber(pageNumber, Category.of(categoryName), modelMap, productList);

    }

    @RequestMapping(value = "/category/{categoryName}/page/{number}", method = RequestMethod.POST)
    public @ResponseBody String addToCart(@RequestBody final String productName,
                                          @PathVariable String categoryName, @PathVariable String number){
        String name = productName.split("=")[1].replace("+", " ");
        Product product = productService.findByName(name);
        shoppingCart.add(product);
        return "redirect:/category/" + categoryName + "/page/" + number;
    }

    private List<Product> getProductListByCategory(Category category) {
        if ("ALL".equals(category.getName())) {
            return productService.findAllAvailable(); // products with count more then 0
        } else {
            return productService.findAvailableByCategory(category);
        }
    }

    private int validatePageNumber(String numberString){
        try {
            int number = Integer.parseInt(numberString);
            number = number > 0 ? number : -1;
            return number;
        } catch (NumberFormatException e){
            return -1;
        }
    }

    // return page depends on current page number
    private String switchPageByPageNumber(int page, Category category, ModelMap modelMap, List<Product> products){
        switch (page){
            case -1:{
                // In this case in pass variable page number is less then 1 or not a number
                return "redirect:/category/" + category.getName().toLowerCase() + "/page/" + 1;
            }case 1:{
                // Make button prev disable
                modelMap.addAttribute("prevDisabled", "disabled='true'"); //set button prev inactive on the first page
            }default:{
                int fromIndex = (page - 1) * PRODUCTS_PER_PAGE; //index of first render product
                int toIndex = page * PRODUCTS_PER_PAGE; //index of last render product
                if(toIndex >= products.size()){
                    toIndex = products.size();
                    modelMap.addAttribute("nextDisabled", "disabled='true'"); //set button next inactive on the last page
                }
                if(toIndex < fromIndex){
                    return "redirect:/category/" + category.getName().toLowerCase() + "/page/" + 1;
                } else {
                    List<Product> productsPerPage = products.subList(fromIndex, toIndex);
                    modelMap.addAttribute("products", productsPerPage);
                    return "index";
                }
            }
        }
    }
}

