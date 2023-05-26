package com.example.shop.service;

import com.example.shop.entity.Clothe;
import com.example.shop.repository.IClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Clothe> getByNameClothe(String name){
        List<Clothe> listTemp = new ArrayList<>();
        for (Clothe clothe : getAllClothes()){
            if(clothe.getName().contains(name)){
                listTemp.add(clothe);
            }
        }
        return listTemp;
    }

    public List<Clothe> getByPrice(double priceStart, double priceEnd, List<Clothe> list){
        List<Clothe> listTemp = new ArrayList<>();
        for (Clothe clothe: list){
            if(clothe.getPrice() >= priceStart && clothe.getPrice() <= priceEnd){
                listTemp.add(clothe);
            }
        }
        return listTemp;
    }

    public List<Clothe> getByPrice(double priceStart, double priceEnd){
        List<Clothe> listTemp = new ArrayList<>();
        for (Clothe clothe: getAllClothes()){
            if(clothe.getPrice() >= priceStart && clothe.getPrice() <= priceEnd){
                listTemp.add(clothe);
            }
        }
        return listTemp;
    }

    public List<Clothe> getByCategory(int idCategory, List<Clothe> list) {
        List<Clothe> listTemp = new ArrayList<>();
        for (Clothe clothe : listTemp) {
            if (clothe.getCategory().getId() == idCategory) {
                listTemp.add(clothe);
            }
        }
        return listTemp;
    }

    public List<Clothe> getByCategory(long idCategory){
        List<Clothe> listTemp = getAllClothes();
        List<Clothe> list = new ArrayList<>();
        for (Clothe clothe : listTemp){
            if(clothe.getCategory().getId() == idCategory){
                list.add(clothe);
            }
        }
        return list;
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
