package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<PlaceOrder, Long> {
    PlaceOrder findByOrderId(int orderId);
    List<PlaceOrder> findAll();
}
