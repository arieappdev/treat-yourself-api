package org.yearup.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class CategoriesControllerTest {

    private CategoriesController controller;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    @BeforeEach
    void setUp () {
        categoryDao = mock(CategoryDao.class);
        productDao = mock(ProductDao.class);
        controller = new CategoriesController(categoryDao, productDao);
    }

    @Test
    void getAll_ShouldReturnAllCategories() {
        // Arrange
        List<Category> mockCategories = Arrays.asList(
                new Category(1, "Electronics", "Tech stuff"),
                new Category(2, "Clothing", "Apparel")
        );
        when(categoryDao.getAllCategories()).thenReturn(mockCategories);

        // Act
        List<Category> result = controller.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Electronics", result.get(0).getName());
        verify(categoryDao, times(1)).getAllCategories();
    }

    @Test
    void getById_ShouldReturnCategory() {
        // Arrange
        Category mockCategory = new Category(1, "Electronics", "Tech stuff");
        when(categoryDao.getById(1)).thenReturn(mockCategory);

        // Act
        Category result = controller.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        assertEquals("Electronics", result.getName());
        verify(categoryDao, times(1)).getById(1);
    }

    @Test
    void getProductsById_ShouldReturnProductList() {
        // Arrange
        List<Product> mockProducts = Arrays.asList(
                new Product(1, "Phone", "Smartphone", 500, 10, 1),
                new Product(2, "Laptop", "Computer", 1000, 5, 1)
        );
        when(productDao.listByCategoryId(1)).thenReturn(mockProducts);

        // Act
        List<Product> result = controller.getProductsById(1);

        // Assert
        assertEquals(2, result.size());
        verify(productDao, times(1)).listByCategoryId(1);
    }

    @Test
    void addCategory_ShouldReturnCreatedCategory() {
        // Arrange
        Category newCategory = new Category(0, "Books", "All kinds of books");
        Category savedCategory = new Category(3, "Books", "All kinds of books");

        when(categoryDao.create(newCategory)).thenReturn(savedCategory);

        // Act
        Category result = controller.addCategory(newCategory);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getCategoryId());
        verify(categoryDao, times(1)).create(newCategory);
    }

    @Test
    void updateCategory_ShouldCallUpdateMethod() {
        // Arrange
        Category updatedCategory = new Category(1, "Updated", "Updated description");

        // Act
        controller.updateCategory(1, updatedCategory);

        // Assert
        verify(categoryDao, times(1)).update(1, updatedCategory);
    }

    @Test
    void deleteCategory_ShouldCallDeleteMethod() {
        // Act
        controller.deleteCategory(1);

        // Assert
        verify(categoryDao, times(1)).delete(1);
    }
}