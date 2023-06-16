package com.example.shop.service;

import com.example.shop.entity.User;
import com.example.shop.repository.IRoleRepository;
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
    @Autowired
    private IRoleRepository roleRepository;
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

    public void save(User user){
        userRepository.save(user);

        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if(roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId, roleId);
        }
    }
    public void deteleUser(Long id){userRepository.deleteById(id);}
}
