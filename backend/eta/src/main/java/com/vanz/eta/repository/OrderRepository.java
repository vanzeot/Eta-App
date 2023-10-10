package com.vanz.eta.repository;

import com.vanz.eta.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findFirstByOrderByIdDesc();

    Optional<Order> findByNumber(@RequestParam("number") String number);
}
