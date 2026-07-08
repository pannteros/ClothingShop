package com.eshop.clothingshop.controller;

import com.eshop.clothingshop.model.Order;
import com.eshop.clothingshop.model.Product;
import com.eshop.clothingshop.service.OrderService;
import com.eshop.clothingshop.service.ProductService;
import com.eshop.clothingshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    // Admin dashboard
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productService.getAllProducts().size());
        model.addAttribute("totalOrders", orderService.getAllOrders().size());
        model.addAttribute("totalUsers", userService.findAll().size());
        return "admin/dashboard";
    }

    // Λίστα προϊόντων (admin)
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    // Form νέου προϊόντος
    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product-form";
    }

    // Form επεξεργασίας προϊόντος
    @GetMapping("/products/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Προϊόν δεν βρέθηκε"));
        model.addAttribute("product", product);
        return "admin/product-form";
    }

    // Αποθήκευση προϊόντος
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Το προϊόν αποθηκεύτηκε επιτυχώς!");
        return "redirect:/admin/products";
    }

    // Διαγραφή προϊόντος
    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Το προϊόν διαγράφηκε.");
        return "redirect:/admin/products";
    }

    // Λίστα παραγγελιών
    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", Order.OrderStatus.values());
        return "admin/orders";
    }

    // Ενημέρωση κατάστασης παραγγελίας
    @PostMapping("/orders/{id}/status")
    public String updateOrderStatus(@PathVariable Long id,
                                    @RequestParam Order.OrderStatus status,
                                    RedirectAttributes redirectAttributes) {
        orderService.updateStatus(id, status);
        redirectAttributes.addFlashAttribute("success", "Η κατάσταση ενημερώθηκε.");
        return "redirect:/admin/orders";
    }
}
