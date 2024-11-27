package com.Assignment.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Assignment.demo.entity.Category;
import com.Assignment.demo.entity.Product;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	
	
	
}
