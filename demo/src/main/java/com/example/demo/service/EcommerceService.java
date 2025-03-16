package com.example.demo.service;

import com.example.demo.dto.EcommerceDto;
import com.example.demo.entity.Ecommerce;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.EcommerceMapper;
import com.example.demo.repository.EcommerceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EcommerceService {
    @Autowired
    private EcommerceMapper ecommerceMapper;
    @Autowired
    private EcommerceRepo ecommerceRepo;

    public List<EcommerceDto> getAllProducts(){
        return ecommerceRepo.findAll().stream()
                .map(ecommerceMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public EcommerceDto getProductById(int id){
        Ecommerce ecommerce =  ecommerceRepo.findById(id).orElseThrow(ProductNotFoundException::new);
        return ecommerceMapper.entityToDto(ecommerce);
    }

    public EcommerceDto addProduct(EcommerceDto ecommerceDto){
        Ecommerce ecommerce = ecommerceMapper.dtoToEntity(ecommerceDto);
        Ecommerce savedEcommerce = ecommerceRepo.save(ecommerce);
        return ecommerceMapper.entityToDto(savedEcommerce);
    }

//    public EcommerceDto updateProduct(int id){
//        Ecommerce existingEcommerce = ecommerceRepo.findById(id).orElse(null);
//        existingEcommerce.setName("Bread");
//        existingEcommerce.setBrand("Premium");
//        existingEcommerce.setQuantityAvailable(3);
//        Ecommerce updatedEcommerce = ecommerceRepo.save(existingEcommerce);
//        return ecommerceMapper.entityToDto(updatedEcommerce);
//    }

    public void deleteProduct(int id){
        ecommerceRepo.deleteById(id);
    }

}

