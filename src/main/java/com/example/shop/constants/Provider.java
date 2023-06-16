package com.example.shop.constants;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Provider {
    LOCAL("Local"),
    GOOGLE("Google");
    public final String value;
}