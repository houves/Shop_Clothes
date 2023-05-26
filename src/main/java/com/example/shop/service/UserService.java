package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.User;
import com.example.shop.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    public void save(User user){
        userRepository.save(user);
    }

    public List<Cart> getAllCartByUser(Long id_user){
        //userRepository.findAllById(id_user);
        return null;

    }
}
