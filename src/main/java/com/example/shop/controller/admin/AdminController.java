package com.example.shop.controller.admin;


import com.example.shop.entity.User;
import com.example.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String homeAdmin(){return "admin/index";}
    @GetMapping("/listNV")
    public String showAllNV(){return "admin/listNV";}

    @GetMapping("/adminUser/listAdminUser")
    public String showAllUser(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "/admin/adminUser/listAdminUser";
    }
    @GetMapping("/adminUser/deleteAdminUser/{id}")
    public String deleteClotheAdmin(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        userService.deteleUser(id);
        return "redirect:/admin/adminUser/listAdminUser";
    }
}
