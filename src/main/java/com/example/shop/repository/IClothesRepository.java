package com.example.shop.repository;

import com.example.shop.entity.Clothe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClothesRepository extends JpaRepository<Clothe, Long> {
}
