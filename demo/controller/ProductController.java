package com.Assignment.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.Assignment.demo.entity.Product;
import com.Assignment.demo.repository.CategoryRepository;
import com.Assignment.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 @Autowired
	    private ProductService productService;
	 
	 private CategoryRepository categoryRepository;

	    @GetMapping
	    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	        return productService.getAllProducts(page, size);
	    }

	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product savedProduct = productService.createProduct(product);
	        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	    }


	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Product product = productService.getProductById(id);
	        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
	    }
	    
	    
//	    @GetMapping("/{categoryId}")
//	    public ResponseEntity<Page<Product>> getProductsByCategoryId(
//	            @PathVariable("categoryId") Long categoryId, 
//	            @RequestParam(defaultValue = "0") int page, 
//	            @RequestParam(defaultValue = "10") int size) {
//	        if(size>100) {
//	        	size=100;
//	        }
//	        Pageable pageable = PageRequest.of(page, size);
//	        
//	        Page<Product> productPage = productService.findProductsByCategoryId(categoryId, pageable);
//
//	        // Return the paginated response
//	        return new ResponseEntity<>(productPage, HttpStatus.OK);
//	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	        Product updatedProduct = productService.updateProduct(id, product);
	        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	    }
}
