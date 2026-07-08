package com.eshop.clothingshop.service;

import com.eshop.clothingshop.model.Order;
import com.eshop.clothingshop.model.OrderItem;
import com.eshop.clothingshop.model.Product;
import com.eshop.clothingshop.model.User;
import com.eshop.clothingshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUserOrderByOrderDateDesc(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(String username, List<Long> productIds, List<Integer> quantities, String address) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Χρήστης δεν βρέθηκε"));

        Order order = Order.builder()
                .user(user)
                .shippingAddress(address)
                .status(Order.OrderStatus.PENDING)
                .build();

        for (int i = 0; i < productIds.size(); i++) {
            Product product = productService.findById(productIds.get(i))
                    .orElseThrow(() -> new RuntimeException("Προϊόν δεν βρέθηκε"));

            int qty = quantities.get(i);
            if (product.getStock() < qty) {
                throw new RuntimeException("Ανεπαρκές απόθεμα για: " + product.getName());
            }

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(qty)
                    .price(product.getPrice())
                    .build();

            order.getItems().add(item);
            product.setStock(product.getStock() - qty);
            productService.save(product);
        }

        order.calculateTotal();
        return orderRepository.save(order);
    }

    @Transactional
    public void updateStatus(Long orderId, Order.OrderStatus status) {
        orderRepository.findById(orderId).ifPresent(o -> {
            o.setStatus(status);
            orderRepository.save(o);
        });
    }
}
