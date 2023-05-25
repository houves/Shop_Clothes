package com.example.shop.controller;

import com.example.shop.entity.Clothe;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ClothesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clothes")
public class ClotheController {
    @Autowired
    private ClothesService clothesService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllClothes(Model model){
        List<Clothe> clothes = clothesService.getAllClothes();
        model.addAttribute("clothes", clothes);
        return "clothe/list";
    }
    @GetMapping("/add")
    public String addClotheForm(Model model){
        model.addAttribute("clothe", new Clothe());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "clothe/add";
    }
    @PostMapping("/add")
    public String addClothe(@Valid @ModelAttribute("clothe") Clothe clothe, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "clothe/add";
        }
        clothesService.addClothes(clothe);
        return "redirect:/clothes";
    }
    @GetMapping("/edit/{id}")
    public String editClotheForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        Clothe clothe = clothesService.getClothesById(id);
        if (clothe != null){
            model.addAttribute("clothe", clothe);
            return "clothe/edit";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editClothe(@ModelAttribute("clothe") Clothe clothe) {
        clothesService.updateClothes(clothe);
        return "redirect:/clothes";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model){
        Clothe clothe = clothesService.getClothesById(id);
        clothesService.deleteClothes(id);
        return "redirect:/clothes";
    }
}
