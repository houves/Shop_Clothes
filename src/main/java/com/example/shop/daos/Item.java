package com.example.shop.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long clotheId;
    private String clotheName;
    //    private String clotheImage;
    private Double price;
    private int quantity;
}
