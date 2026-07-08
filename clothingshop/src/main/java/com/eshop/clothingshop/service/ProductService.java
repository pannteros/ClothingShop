package com.eshop.clothingshop.service;

import com.eshop.clothingshop.model.Product;
import com.eshop.clothingshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllAvailableProducts() {
        return productRepository.findByAvailableTrue();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategoryAndAvailableTrue(category);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCaseAndAvailableTrue(name);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void toggleAvailability(Long id) {
        productRepository.findById(id).ifPresent(p -> {
            p.setAvailable(!p.isAvailable());
            productRepository.save(p);
        });
    }
}
