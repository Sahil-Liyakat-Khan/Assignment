package com.Assignment.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.Assignment.demo.entity.Category;
import com.Assignment.demo.entity.Product;
import com.Assignment.demo.repository.CategoryRepository;
import com.Assignment.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product createProduct(Product product) {
        // Fetch the category from the DB by ID
        Category category = categoryRepository.findById(product.getCategory().getId())
                                              .orElseThrow(() -> new RuntimeException("Category not found"));

        // Associate the category with the product
        product.setCategory(category);

        return productRepository.save(product);
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    
    public Page<Product> findProductsByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findProductsByCategoryId(categoryId, pageable);
    }

    public Product updateProduct(Long id, Product product) {
        // Check if the product exists
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update the product details
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        // Ensure the category is set (if it's provided)
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        // Save and return the updated product
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
