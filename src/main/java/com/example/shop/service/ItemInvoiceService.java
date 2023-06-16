package com.example.shop.service;

import com.example.shop.entity.ItemInvoice;
import com.example.shop.repository.IItemInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInvoiceService {
    @Autowired
    private IItemInvoiceRepository itemInvoiceRepository;
    public List<ItemInvoice> getAllItemInvoice(){
        return itemInvoiceRepository.findAll();
    }
}
