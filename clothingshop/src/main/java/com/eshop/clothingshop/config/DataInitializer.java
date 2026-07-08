package com.eshop.clothingshop.config;

import com.eshop.clothingshop.model.Product;
import com.eshop.clothingshop.model.User;
import com.eshop.clothingshop.repository.ProductRepository;
import com.eshop.clothingshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Δημιουργία admin αν δεν υπάρχει
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@clothingshop.gr")
                    .password(passwordEncoder.encode("admin123"))
                    .firstName("Admin")
                    .lastName("Shop")
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(admin);
            log.info("✅ Admin user created: admin / admin123");
        }

        // Δημιουργία 2 προϊόντων αν δεν υπάρχουν
        if (productRepository.count() == 0) {

            Product tshirt = Product.builder()
                    .name("Classic White T-Shirt")
                    .description("Κλασική λευκή μπλούζα από 100% βαμβάκι. Άνετη εφαρμογή για καθημερινή χρήση.")
                    .price(new BigDecimal("19.99"))
                    .category("Μπλούζες")
                    .size("M")
                    .color("Λευκό")
                    .stock(50)
                    .imageUrl("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400")
                    .available(true)
                    .build();

            Product jeans = Product.builder()
                    .name("Slim Fit Jeans")
                    .description("Στιλάτο slim fit τζιν παντελόνι σε σκούρο μπλε χρώμα. Ιδανικό για κάθε περίσταση.")
                    .price(new BigDecimal("49.99"))
                    .category("Παντελόνια")
                    .size("32")
                    .color("Σκούρο Μπλε")
                    .stock(30)
                    .imageUrl("https://images.unsplash.com/photo-1542272604-787c3835535d?w=400")
                    .available(true)
                    .build();

            productRepository.save(tshirt);
            productRepository.save(jeans);
            log.info("✅ 2 products created: T-Shirt & Jeans");
        }
    }
}
