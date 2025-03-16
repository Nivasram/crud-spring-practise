package com.example.demo.controllerTest;

import com.example.demo.controller.EcommerceController;
import com.example.demo.dto.EcommerceDto;
import com.example.demo.service.EcommerceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EcommerceControllerTest {
    @Mock
    private EcommerceService ecommerceService;

    @InjectMocks
    private EcommerceController ecommerceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ecommerceController).build();
    }

    @Test
    void testGetAllProducts() throws Exception {
        EcommerceDto ecommerceDto = new EcommerceDto(1, "Bread", 35.00);
        List<EcommerceDto> ecommerceDtoList = List.of(ecommerceDto);

        when(ecommerceService.getAllProducts()).thenReturn(ecommerceDtoList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Bread"));
    }

    @Test
    void testGetProductById() throws Exception {
        EcommerceDto ecommerceDto = new EcommerceDto(1, "Bread", 35.00);

        when(ecommerceService.getProductById(1)).thenReturn(ecommerceDto);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bread"));
    }

    @Test
    void testAddProduct() throws Exception {

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\" : 1, \"name\" : \"Bread\", \"price\" : 35.00}"))
                .andExpect(status().isOk());

        verify(ecommerceService, times(1)).addProduct(any(EcommerceDto.class));
    }

    @Test
    void testDeleteProducts() throws  Exception {

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());

        verify(ecommerceService, times(1)).deleteProduct(1);
    }
}
