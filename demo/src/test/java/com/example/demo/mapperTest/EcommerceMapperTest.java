package com.example.demo.mapperTest;

import com.example.demo.dto.EcommerceDto;
import com.example.demo.entity.Ecommerce;
import com.example.demo.mapper.EcommerceMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EcommerceMapperTest {

    @Test
    void testEntityToDto() {
        Ecommerce ecommerce = new Ecommerce(1, "Bread", 35.00);
        EcommerceDto ecommerceDto = EcommerceMapper.INSTANCE.entityToDto(ecommerce);

        assertEquals(1, ecommerceDto.getId());
        assertEquals("Bread", ecommerceDto.getName());
        assertEquals(35.00, ecommerceDto.getPrice());
    }

    @Test
    void testDtoToEntity(){
        EcommerceDto ecommerceDto = new EcommerceDto(1, "Bread", 35.00);
        Ecommerce ecommerce = EcommerceMapper.INSTANCE.dtoToEntity(ecommerceDto);

        assertEquals(1, ecommerce.getId());
        assertEquals("Bread", ecommerce.getName());
        assertEquals(35.00, ecommerce.getPrice());
    }
}
