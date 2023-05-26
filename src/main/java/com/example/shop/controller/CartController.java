package com.example.shop.controller;

import com.example.shop.entity.Cart;
import com.example.shop.entity.User;
import com.example.shop.repository.IUserRepository;
import com.example.shop.service.CartService;
import com.example.shop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    //@Autowired
    //private CartService cartService;

    @Autowired
    private UserService userService;
/*
    @RequestMapping("/listCart")
    public String listCart(Authentication authentication, Model model){
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userService.(username);
            if (user != null) {
                Long userId = user.getId();
                List<Cart> cartList = cartService.getCartByUserId(userId);
                double totalPrice = 0;
                for (Cart cart: cartList ) {
                    totalPrice = cart.getQuantity() * cart.getClothe().getPrice();
                }

                model.addAttribute("totalPrice", totalPrice);
                model.addAttribute("carts", cartList);
                return "cart/index";
            }
        }
        return "home/index";
    }
*/

/*

    @RequestMapping("/addCart/{id}")
    public String addCart(HttpServletRequest request,HttpSession session,@PathVariable long id){
        HashMap<Long, Cart> cart = (HashMap<Long, Cart>) session.getAttribute("cart");
        if(cart == null) {
            cart = new HashMap<Long, Cart>();
        }
        cart = cartService.addCart(id, cart);
        session.setAttribute("cart", cart);
        totalPrice = cartService.totalPrice(cart);
        totalQuantity = cartService.totalQuantities(cart);
        session.setAttribute("totalPriceCart", totalPrice);
        session.setAttribute("totalQuantitiesCart", totalQuantity);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/editCart/{id}/{quantity}")
    public String editCart(HttpServletRequest request,HttpSession session,@PathVariable long id, @PathVariable int quantity){
        HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) session.getAttribute("cart");
        if(cart == null) {
            cart = new HashMap<Long, CartDTO>();
        }
        cart = cartService.editCart(id, quantity ,cart);
        session.setAttribute("cart", cart);
        totalPrice = cartService.totalPrice(cart);
        totalQuantity = cartService.totalQuantities(cart);
        session.setAttribute("totalPriceCart", totalPrice);
        session.setAttribute("totalQuantitiesCart", totalQuantity);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/deleteCart/{id}")
    public String deleteCart(HttpServletRequest request,HttpSession session,@PathVariable long id){
        HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) session.getAttribute("cart");
        if(cart == null) {
            cart = new HashMap<Long, CartDTO>();
        }
        cart = cartService.deleteCart(id, cart);
        totalPrice = cartService.totalPrice(cart);

        totalQuantity = cartService.totalQuantities(cart);
        session.setAttribute("totalPriceCart", totalPrice);
        session.setAttribute("totalQuantitiesCart", totalQuantity);
        return "redirect:" + request.getHeader("Referer");
    }
*/
}
