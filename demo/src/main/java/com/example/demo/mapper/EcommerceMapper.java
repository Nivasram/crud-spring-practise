package com.example.demo.mapper;

import com.example.demo.dto.EcommerceDto;
import com.example.demo.entity.Ecommerce;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EcommerceMapper {

    EcommerceMapper INSTANCE = Mappers.getMapper(EcommerceMapper.class);

    EcommerceDto entityToDto(Ecommerce ecommerce);

    Ecommerce dtoToEntity(EcommerceDto ecommerceDto);
}
