package com.example.shop.repository;

import  com.example.shop.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long>{
}
