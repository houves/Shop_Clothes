package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.User;
import com.example.shop.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public User getUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }
    public List<User> getByNameUser(String name){
        List<User> listTemp = new ArrayList<>();
        for (User user : getAllUser()){
            if(user.getName().contains(name)){
                listTemp.add(user);
            }
        }
        return listTemp;
    }

    public List<Cart> getAllCartByUser(Long id_user){
        //userRepository.findAllById(id_user);
        return null;

    }
    public void save(User user){
        userRepository.save(user);
    }
    public void deteleUser(Long id){userRepository.deleteById(id);}
}
