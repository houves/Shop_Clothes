package com.example.shop.daos;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
public class Cart {
    private List<Item> cartItems = new ArrayList<>();
    public void addItems(Item item) {
        boolean isExist = cartItems.stream()
                .filter(i -> Objects.equals(i.getClotheId(),
                        item.getClotheId()))
                .findFirst()
                .map(i -> {
                    i.setQuantity(i.getQuantity() +
                            item.getQuantity());
                    return true;
                })
                .orElse(false);
        if (!isExist) {
            cartItems.add(item);
        }
    }
    public void removeItems(Long clotheId) {
        cartItems.removeIf(item -> Objects.equals(item.getClotheId(),
                clotheId));
    }
    public void updateItems(Long clotheId, int quantity) {
        cartItems.stream()
                .filter(item -> Objects.equals(item
                        .getClotheId(), clotheId))
                .forEach(item -> item.setQuantity(quantity));
    }
}