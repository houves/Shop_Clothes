package com.example.shop.controller.admin;

import com.example.shop.entity.Category;
import com.example.shop.entity.Clothe;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ClothesService;
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
@RequestMapping(value ="/admin/adminClothe")
public class AdminClotheController {
    @Autowired
    private ResourceLoader resourceLoader;

    public void uploadFile(String name, double price, long categoryID, MultipartFile file, long id){
        try{
            Category categoryTemp = categoryService.getCategoryById(categoryID);
            Clothe clothe = new Clothe();
            clothe.setName(name);
            clothe.setPrice(price);
            clothe.setImage(file.getOriginalFilename());
            clothe.setCategory(categoryTemp);

            if(id == -1){
                clothesService.addClothes(clothe);
            }else{
                clothe.setId(id);
                clothesService.updateClothes(clothe);
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
    private ClothesService clothesService;
    @Autowired
    private CategoryService categoryService;
    private final List<String> arrayPrice(){
        String[] arrayPriceTemp = {"0->200000", "200000->400000", "400000->600000", "600000->800000", "800000->1000000"};
        return Arrays.asList(arrayPriceTemp);
    }
    @GetMapping("/listClotheAdmin")
    public String showAllClothesAdmin(Model model){
        List<Clothe> clothes = clothesService.getAllClothes();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("clothes", clothes);
        model.addAttribute("prices", arrayPrice());
        model.addAttribute("categories", categories);
        return "admin/adminClothe/listClotheAdmin";
    }
    @GetMapping("/addClotheAdmin")
    public String addClotheAdminForm(Model model){
        model.addAttribute("clothe", new Clothe());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/adminClothe/addClotheAdmin";
    }
    @PostMapping("/addClotheAdmin")
    public String addClotheAdmin(@RequestParam("name") String name,
                                 @RequestParam("price") double price,
                                 @RequestParam("category") long category,
                                 @RequestParam("image") MultipartFile image)
    {
        uploadFile(name, price, category, image, -1);
        return "redirect:/admin/adminClothe/listClotheAdmin";
    }
    @GetMapping("/editClotheAdmin/{id}")
    public String editClotheAdminForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        Clothe clothe = clothesService.getClothesById(id);
        if (clothe != null){
            model.addAttribute("clothe", clothe);
            return "admin/adminClothe/editClotheAdmin";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/editClotheAdmin")
    public String editClotheAdmin(@RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("category") long category, @RequestParam("image") MultipartFile image, @RequestParam("id") long id) {
        uploadFile(name, price, category, image, id);
        return "redirect:/admin/adminClothe/listClotheAdmin";
    }
        @GetMapping("/deleteClotheAdmin/{id}")
    public String deleteClotheAdmin(@PathVariable("id") Long id, Model model){
        Clothe clothe = clothesService.getClothesById(id);
        clothesService.deleteClothes(id);
        return "redirect:/admin/adminClothe/listClotheAdmin";
    }
}
