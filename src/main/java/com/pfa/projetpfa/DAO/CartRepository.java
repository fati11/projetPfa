package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.Bufcart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Bufcart, Long> {
    List<Bufcart> findByEmail(String email);

    Bufcart findByBufcartIdAndEmail(int bufcartId, String email);

    void deleteByBufcartIdAndEmail(int bufcartId, String email);

    List<Bufcart> findAllByEmail(String email);

    List<Bufcart> findAllByOrderId(int orderId);
}
