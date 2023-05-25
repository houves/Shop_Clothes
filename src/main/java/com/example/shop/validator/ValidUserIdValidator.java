package com.example.shop.validator;

import com.example.shop.entity.User;
import com.example.shop.validator.annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user == null){
            return true;
        }
        return user.getId() != null;
    }
}
