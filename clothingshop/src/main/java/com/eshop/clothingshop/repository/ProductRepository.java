package com.eshop.clothingshop.repository;

import com.eshop.clothingshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByAvailableTrue();

    List<Product> findByCategoryAndAvailableTrue(String category);

    List<Product> findByNameContainingIgnoreCaseAndAvailableTrue(String name);

    List<Product> findByCategory(String category);
}
