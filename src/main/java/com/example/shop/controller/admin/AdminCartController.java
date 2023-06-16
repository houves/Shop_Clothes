package com.example.shop.controller.admin;

import com.example.shop.entity.Category;
import com.example.shop.entity.Clothe;
import com.example.shop.entity.Invoice;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ClothesService;
import com.example.shop.service.InvoiceService;
import com.example.shop.service.ItemInvoiceService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value ="/admin/adminCart")
public class AdminCartController {
    @Autowired
    private ClothesService clothesService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ItemInvoiceService itemInvoiceService;
    @GetMapping
    public String showAllCartAdmin(Model model){
        List<Invoice> invoices = invoiceService.getAllInvoice();
        model.addAttribute("invoices", invoices);
        return "admin/adminCart/list";
    }
    @GetMapping("/info/{id}")
    public String showAllCartInfoAdmin(Model model,@PathVariable("id") Long id){
        model.addAttribute("itemInvoice", itemInvoiceService.getAllItemInvoice());
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice != null){
            model.addAttribute("invoice", invoice);
            return "admin/adminCart/info";
        }else {
            return "not-found";
        }
    }

    @GetMapping("/deleteClotheAdmin/{id}")
    public String deleteClotheAdmin(@PathVariable("id") Long id, Model model){
        Clothe clothe = clothesService.getClothesById(id);
        clothesService.deleteClothes(id);
        return "redirect:/admin/adminClothe/listClotheAdmin";
    }

}
