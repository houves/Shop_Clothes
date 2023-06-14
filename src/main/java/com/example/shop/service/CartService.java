package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.repository.ICartReponsitory;
import com.example.shop.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ICartReponsitory cartReponsitory;

    //public List<Cart> getCartByUserId(Long userId) {
        //return cartReponsitory.findAllByIdUser(userId);
   // }
    public Cart getCartById(Long id){
        Optional<Cart> optionalCart = cartReponsitory.findById(id);
        if(optionalCart.isPresent()){
            return optionalCart.get();
        }else {
            throw new RuntimeException("Category not found");
        }
    }
    public Cart saveCart(Cart cart){return cartReponsitory.save(cart);}
    public void deleteCart(Long id){cartReponsitory.deleteById(id);}
}
