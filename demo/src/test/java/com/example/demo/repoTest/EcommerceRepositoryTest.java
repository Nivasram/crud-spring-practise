package com.example.demo.repoTest;

import com.example.demo.entity.Ecommerce;
import com.example.demo.repository.EcommerceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class EcommerceRepositoryTest {

    @Mock
    private EcommerceRepo ecommerceRepo;

    private Ecommerce ecommerce;

    @BeforeEach
    void setUp() {
        ecommerce = new Ecommerce();
        ecommerce.setName("Bread");
        ecommerce.setPrice(35.00);
        ecommerceRepo.save(ecommerce);
    }

    @Test
    void testGetAllProduct() {
        List<Ecommerce> ecommerceList = ecommerceRepo.findAll();

        assertEquals("Bread", ecommerce.getName());
        assertEquals(35.00, ecommerce.getPrice());
    }

    @Test
    void testGetProductById() {
        Optional<Ecommerce> foundEcommerce = ecommerceRepo.findById(ecommerce.getId());

        assertTrue(foundEcommerce.isPresent());
        assertEquals(ecommerce.getPrice(), foundEcommerce.get().getPrice());
    }

    @Test
    void TestDeleteProduct() {
        // Arrange
        List<Ecommerce> products = ecommerceRepo.findAll();
        int productId = products.getFirst().getId();

        // Act
        ecommerceRepo.deleteById(productId);
        Optional<Ecommerce> deletedProduct = ecommerceRepo.findById(productId);

        // Assert
        assertFalse(deletedProduct.isPresent());
    }

    @Test
    void testAddProduct() {
        // Arrange
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setName("Bread");

        // Act
        Ecommerce savedEcommerce = ecommerceRepo.save(ecommerce);

        // Assert
        assertNotNull(savedEcommerce);
        assertEquals("Bread", savedEcommerce.getName());
    }
}

