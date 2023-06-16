package com.example.shop.controller;

import com.example.shop.entity.Category;
import com.example.shop.entity.Clothe;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ClothesService clothesService;

    @Autowired
    private CategoryService categoryService;

    private final List<String> arrayPrice(){
        String[] arrayPriceTemp = {"0->200000", "200000->400000", "400000->600000", "600000->800000", "800000->1000000"};
        return Arrays.asList(arrayPriceTemp);
    }

    @GetMapping
    public String showAllClothes(Model model){
        List<Clothe> clothes = clothesService.getAllClothes();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("clothes", clothes);
        model.addAttribute("prices", arrayPrice());
        model.addAttribute("categories", categories);
        return "home/index";
    }
}
