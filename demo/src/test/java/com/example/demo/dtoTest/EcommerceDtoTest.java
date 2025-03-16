package com.example.demo.dtoTest;

import com.example.demo.dto.EcommerceDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EcommerceDtoTest {

    @Test
    void testEcommerceDto() {
        EcommerceDto ecommerceDto = new EcommerceDto(1, "Bread", 35.00);

        assertEquals(1, ecommerceDto.getId());
        assertEquals("Bread", ecommerceDto.getName());
        assertEquals(35.00, ecommerceDto.getPrice());
    }
}
