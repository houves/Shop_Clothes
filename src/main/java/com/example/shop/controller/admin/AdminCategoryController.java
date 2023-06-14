package com.example.shop.controller.admin;

import com.example.shop.entity.Category;
import com.example.shop.entity.Clothe;
import com.example.shop.service.CategoryService;
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
import java.util.List;

@Controller
@RequestMapping(value ="/admin/adminCategory")
public class AdminCategoryController {
    @Autowired
    private ResourceLoader resourceLoader;

    public void uploadFile(String name, MultipartFile file, long id){
        try{
            Category category = new Category();
            category.setName(name);
            category.setImage(file.getOriginalFilename());

            if(id == -1){
                categoryService.addCategory(category);
            }else{
                category.setId(id);
                categoryService.saveCategory(category);
            }

            byte[] bytes = file.getBytes();
            String tempFolderPath = System.getProperty("java.io.tmpdir");
            Path tempFilePath = Paths.get(tempFolderPath, file.getOriginalFilename());
            Files.write(tempFilePath, bytes);

            // Tạo đường dẫn cho file upload
            Resource resource = resourceLoader.getResource("classpath:static/images/");
            String uploadPath = resource.getFile().getAbsolutePath();

            File sourceFile = new File(tempFilePath.toFile().getAbsolutePath());
            File destinationFile = new File(uploadPath + "\\" + sourceFile.getName());
            File systemFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\" + sourceFile.getName());

            // Kiểm tra xem tệp tin đích đã tồn tại hay chưa
            if (destinationFile.exists() && systemFile.exists()) {
                // Xóa tệp tin đích
                FileUtils.forceDelete(destinationFile);
                FileUtils.forceDelete(systemFile);
            }

            FileUtils.copyFile(sourceFile, destinationFile);
            FileUtils.copyFile(sourceFile, systemFile);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/listCategoryAdmin")
    public String showAllCategoryAdmin(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/admin/adminCategory/listCategoryAdmin";
    }
    @GetMapping("/addCategoryAdmin")
    public String addCategoryAdminForm(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/adminCategory/addCategoryAdmin";
    }
    @PostMapping("/addCategoryAdmin")
    public String addCategoryAdmin(@RequestParam("name") String name,
                                 @RequestParam("image") MultipartFile image)
    {
        uploadFile(name, image, -1);
        return "redirect:/admin/adminCategory/addCategoryAdmin";
    }
    @GetMapping("/editCategoryAdmin/{id}")
    public String editCategoryAdminForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        Category category = categoryService.getCategoryById(id);
        if (category != null){
            model.addAttribute("category", category);
            return "admin/adminCategory/editCategoryAdmin";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/editCategoryAdmin")
    public String editClotheAdmin(@RequestParam("name") String name,
                                  @RequestParam("image") MultipartFile image,
                                  @RequestParam("id") long id) {
        uploadFile(name, image, id);
        return "redirect:/admin/adminCategory/listCategoryAdmin";
    }
    @GetMapping("/deleteCategoryAdmin/{id}")
    public String deleteClotheAdmin(@PathVariable("id") Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(id);
        return "redirect:/admin/adminCategory/listCategoryAdmin";
    }
}
