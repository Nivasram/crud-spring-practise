package com.example.demo.serviceTest;

import com.example.demo.dto.EcommerceDto;
import com.example.demo.entity.Ecommerce;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.EcommerceMapper;
import com.example.demo.repository.EcommerceRepo;
import com.example.demo.service.EcommerceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EcommerceServiceTest {

    @InjectMocks
    private EcommerceService ecommerceService;

    @Mock
    private EcommerceMapper ecommerceMapper;

    @Mock
    private EcommerceRepo ecommerceRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_ShouldReturnProductList() {
        // Arrange
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setId(1);
        ecommerce.setName("Test Product");

        EcommerceDto ecommerceDto = new EcommerceDto();
        ecommerceDto.setId(1);
        ecommerceDto.setName("Test Product");

        when(ecommerceRepo.findAll()).thenReturn(Arrays.asList(ecommerce));
        when(ecommerceMapper.entityToDto(ecommerce)).thenReturn(ecommerceDto);

        // Act
        List<EcommerceDto> products = ecommerceService.getAllProducts();

        // Assert
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).getName());
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenExists() {
        // Arrange
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setId(1);
        ecommerce.setName("Test Product");

        EcommerceDto ecommerceDto = new EcommerceDto();
        ecommerceDto.setId(1);
        ecommerceDto.setName("Test Product");

        when(ecommerceRepo.findById(1)).thenReturn(Optional.of(ecommerce));
        when(ecommerceMapper.entityToDto(ecommerce)).thenReturn(ecommerceDto);

        // Act
        EcommerceDto product = ecommerceService.getProductById(1);

        // Assert
        assertNotNull(product);
        assertEquals("Test Product", product.getName());
    }

    @Test
    void getProductById_ShouldThrowException_WhenNotExists() {
        // Arrange
        when(ecommerceRepo.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> ecommerceService.getProductById(1));
    }

    @Test
    void addProduct_ShouldSaveAndReturnProduct() {
        // Arrange
        EcommerceDto ecommerceDto = new EcommerceDto();
        ecommerceDto.setName("New Product");

        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setName("New Product");

        when(ecommerceMapper.dtoToEntity(ecommerceDto)).thenReturn(ecommerce);
        when(ecommerceRepo.save(ecommerce)).thenReturn(ecommerce);
        when(ecommerceMapper.entityToDto(ecommerce)).thenReturn(ecommerceDto);

        // Act
        EcommerceDto savedProduct = ecommerceService.addProduct(ecommerceDto);

        // Assert
        assertNotNull(savedProduct);
        assertEquals("New Product", savedProduct.getName());
    }

    @Test
    void deleteProduct_ShouldCallDeleteById() {
        // Arrange
        int productId = 1;

        // Act
        ecommerceService.deleteProduct(productId);

        // Assert
        verify(ecommerceRepo, times(1)).deleteById(productId);
    }
}
