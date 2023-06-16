package com.example.shop.service;

import com.example.shop.entity.Invoice;
import com.example.shop.repository.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private IInvoiceRepository invoiceRepository;
    public List<Invoice> getAllInvoice(){
        return invoiceRepository.findAll();
    }
    public Invoice getInvoiceById(Long id){
        Optional<Invoice> optional = invoiceRepository.findById(id);
        return optional.orElse(null);
    }
}
