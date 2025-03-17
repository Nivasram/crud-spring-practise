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
        ecommerceRepo.save(ecommerce);
        return ecommerceDto;
    }

    public void deleteProduct(int id){
        if (!ecommerceRepo.existsById(id)){
            throw new ProductNotFoundException();
        }
        ecommerceRepo.deleteById(id);
    }
}
