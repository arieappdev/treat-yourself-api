package org.yearup.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.yearup.data.ProductDao;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class ProductsControllerTest
{
    @Test
    public void search_ShouldReturnFilteredProducts()
    {
        // Arrange
        ProductDao productDao = Mockito.mock(ProductDao.class);
        ProductsController controller = new ProductsController(productDao);

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Test Product", new BigDecimal("50"), 1, "Description", "red", 10, true, "image.jpg"));

        // When the search method is called with these filters, return mockProducts
        Mockito.when(productDao.search(1, new BigDecimal("25"), new BigDecimal("100"), "red")).thenReturn(mockProducts);

        // Act
        List<Product> result = controller.search(1, new BigDecimal("25"), new BigDecimal("100"), "red");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getName());
    }

    @Test
    public void update_ShouldCallDaoUpdate()
    {
        // Arrange
        ProductDao productDao = Mockito.mock(ProductDao.class);
        ProductsController controller = new ProductsController(productDao);

        Product updatedProduct = new Product(1, "Updated Product", new BigDecimal("75"), 1, "New Description", "blue", 5, false, "image2.jpg");

        // Act
        controller.updateProduct(1, updatedProduct);

        // Assert - check that the DAO's update method was called with correct arguments
        verify(productDao).update(1, updatedProduct);
    }
}