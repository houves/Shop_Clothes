package com.example.shop.controller;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Category;
import com.example.shop.entity.Clothe;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ClothesService clothesService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/price/{priceStart}->{priceEnd}")
    public String searchWithPriceStartEnd(@PathVariable double priceStart, @PathVariable double priceEnd, Model model){
       List<Clothe> clothes = clothesService.getByPrice(priceStart, priceEnd);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("clothes", clothes);
        model.addAttribute("prices", arrayPrice());
        model.addAttribute("categories", categories);
        return "search/search";
    }

    private final List<String> arrayPrice(){
        String[] arrayPriceTemp = {"0->200000", "200000->400000", "400000->600000", "600000->800000", "800000->1000000"};
        return Arrays.asList(arrayPriceTemp);
    }

    @GetMapping("/{id_category}")
    public String searchCategory(@PathVariable long id_category, Model model){
        List<Clothe> listTemp = clothesService.getByCategory(id_category);
        List<Category> categories = categoryService.getAllCategories();
        List<String> stringList = arrayPrice();
        model.addAttribute("prices", stringList);
        model.addAttribute("clothes", listTemp);
        model.addAttribute("categories", categories);
        return "search/search";
    }

    @PostMapping("/")
    public String searchParam (@RequestParam("search") String search, Model model){
        if(search.isEmpty()){
            return "clothe/list";
        }else{
            model.addAttribute("clothes", clothesService.getByNameClothe(search));
            return "search/search";
        }
    }

}
