package com.example.shop.service;

import com.example.shop.entity.Clothe;
import com.example.shop.repository.IClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothesService {
    @Autowired
    private IClothesRepository clothesRepository;

    public List<Clothe> getAllClothes(){
        return clothesRepository.findAll();
    }
    public Clothe getClothesById(Long id){
        Optional<Clothe> optional = clothesRepository.findById(id);
        return optional.orElse(null);
    }
    public  void addClothes(Clothe clothe){
        clothesRepository.save(clothe);
    }
    public void updateClothes(Clothe clothe){
        clothesRepository.save(clothe);
    }
    public void deleteClothes(Long id){
        clothesRepository.deleteById(id);
    }
}
