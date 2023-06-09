package com.example.shop.service;

import com.example.shop.entity.Category;
import com.example.shop.entity.Clothe;
import com.example.shop.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    public List<Category> getAllCategories(){return categoryRepository.findAll();}

    public Category getCategoryById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }else {
            throw new RuntimeException("Category not found");
        }
    }
    public List<Category> getByNameCategory(String name){
        List<Category> listTemp = new ArrayList<>();
        for (Category category : getAllCategories()){
            if(category.getName().contains(name)){
                listTemp.add(category);
            }
        }
        return listTemp;
    }
    public Category addCategory(Category category){return categoryRepository.save(category);}
    public Category saveCategory(Category category){return categoryRepository.save(category);}
    public void deleteCategory(Long id){categoryRepository.deleteById(id);}
}
