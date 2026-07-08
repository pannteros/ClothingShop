package com.eshop.clothingshop.repository;

import com.eshop.clothingshop.model.Order;
import com.eshop.clothingshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserOrderByOrderDateDesc(User user);

    List<Order> findAllByOrderByOrderDateDesc();
}
