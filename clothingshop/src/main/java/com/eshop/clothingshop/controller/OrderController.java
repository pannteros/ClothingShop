package com.eshop.clothingshop.controller;

import com.eshop.clothingshop.service.OrderService;
import com.eshop.clothingshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    // Ιστορικό παραγγελιών χρήστη
    @GetMapping
    public String myOrders(Principal principal, Model model) {
        userService.findByUsername(principal.getName()).ifPresent(user -> {
            model.addAttribute("orders", orderService.getOrdersByUser(user));
        });
        return "orders/list";
    }

    // Υποβολή παραγγελίας (από cart)
    @PostMapping("/checkout")
    public String checkout(
            @RequestParam List<Long> productIds,
            @RequestParam List<Integer> quantities,
            @RequestParam String address,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        try {
            orderService.createOrder(principal.getName(), productIds, quantities, address);
            redirectAttributes.addFlashAttribute("success", "Η παραγγελία σας υποβλήθηκε επιτυχώς!");
            return "redirect:/orders";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/products";
        }
    }
}
