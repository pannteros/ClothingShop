package com.eshop.clothingshop.controller;

import com.eshop.clothingshop.model.Product;
import com.eshop.clothingshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Αρχική σελίδα → redirect στα products
    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    // Λίστα προϊόντων
    @GetMapping("/products")
    public String listProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            Model model) {

        List<Product> products;

        if (search != null && !search.isBlank()) {
            products = productService.searchByName(search);
            model.addAttribute("search", search);
        } else if (category != null && !category.isBlank()) {
            products = productService.findByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            products = productService.getAllAvailableProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", List.of("Μπλούζες", "Παντελόνια"));
        return "products/list";
    }

    // Λεπτομέρειες προϊόντος
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Προϊόν δεν βρέθηκε"));
        model.addAttribute("product", product);
        return "products/detail";
    }
}
