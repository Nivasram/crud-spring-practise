package com.example.demo.serviceTest;

import com.example.demo.dto.EcommerceDto;
import com.example.demo.entity.Ecommerce;
import com.example.demo.mapper.EcommerceMapper;
import com.example.demo.repository.EcommerceRepo;
import com.example.demo.service.EcommerceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EcommerceServiceTest {

    @InjectMocks
    private EcommerceService ecommerceService;

    @Mock
    private EcommerceMapper ecommerceMapper;

    @Mock
    private EcommerceRepo ecommerceRepo;

    private Ecommerce ecommerce;

    private EcommerceDto ecommerceDto;

    @BeforeEach
    void setUp() {
         ecommerce = new Ecommerce(1, "Bread", 35.00);
         ecommerceDto = new EcommerceDto(1, "Bread", 35.00);
    }

    @Test
    void testGetAllProducts() {
        List<Ecommerce> ecommerceList = List.of(ecommerce);
        when(ecommerceRepo.findAll()).thenReturn(ecommerceList);
        when(ecommerceMapper.entityToDto(ecommerce)).thenReturn(ecommerceDto);

        List<EcommerceDto> result = ecommerceService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Bread", result.getFirst().getName());
    }

    @Test
    void testGetProductById() {
        when(ecommerceRepo.findById(1)).thenReturn(Optional.of(ecommerce));
        when(ecommerceMapper.entityToDto(ecommerce)).thenReturn(ecommerceDto);

        EcommerceDto result = ecommerceService.getProductById(1);
        assertNotNull(result);
        assertEquals("Bread", result.getName());
    }

    @Test
    void testAddProduct() {
       when(ecommerceMapper.dtoToEntity(ecommerceDto)).thenReturn(ecommerce);
       when(ecommerceRepo.save(ecommerce)).thenReturn(ecommerce);

       EcommerceDto savedEcommerce = ecommerceService.addProduct(ecommerceDto);

       assertEquals("Bread", savedEcommerce.getName());
    }

    @Test
    void testDeleteProduct(){
        when(ecommerceRepo.existsById(1)).thenReturn(true);

        ecommerceService.deleteProduct(1);

        verify(ecommerceRepo, times(1)).deleteById(1);
    }
}
