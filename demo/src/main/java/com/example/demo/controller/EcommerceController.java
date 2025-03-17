package com.example.demo.controller;

import com.example.demo.dto.EcommerceDto;
import com.example.demo.service.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class EcommerceController {
    @Autowired
    private EcommerceService ecommerceService;

    @GetMapping
    public List<EcommerceDto> getAllProduct(){
       return ecommerceService.getAllProducts();
    }

    @GetMapping("/{id}")
    public EcommerceDto getProductById(@PathVariable int id){
        return ecommerceService.getProductById(id);
    }

    @PostMapping
    public EcommerceDto addProduct(@RequestBody EcommerceDto ecommerceDto){
        return ecommerceService.addProduct(ecommerceDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        ecommerceService.deleteProduct(id);
    }

}
