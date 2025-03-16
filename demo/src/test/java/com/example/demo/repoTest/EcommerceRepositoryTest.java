package com.example.demo.repoTest;

import com.example.demo.entity.Ecommerce;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.EcommerceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class EcommerceRepositoryTest {

    @Autowired
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

        assertFalse(ecommerceList.isEmpty());
        assertEquals("Bread", ecommerce.getName());
        assertEquals(35.00, ecommerce.getPrice());
    }

    @Test
    void testGetProductById() {
        Optional<Ecommerce> foundEcommerce = ecommerceRepo.findById(ecommerce.getId());

        assertEquals("Bread",
                foundEcommerce.get().getName());
    }

    @Test
    void testAddProduct() {
        Ecommerce newEcommerce = new Ecommerce();
        newEcommerce.setName("Bread");

        Ecommerce savedEcommerce = ecommerceRepo.save(newEcommerce);

        assertNotNull(savedEcommerce);
        assertEquals("Bread", savedEcommerce.getName());
    }

    @Test
    void TestDeleteProduct() {
      ecommerceRepo.deleteById(ecommerce.getId());

      Optional<Ecommerce> deleteProduct = ecommerceRepo.findById(ecommerce.getId());

      assertFalse(deleteProduct.isPresent());
    }


}

