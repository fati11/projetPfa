package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByDesignationContaining(String key);
    Product findByProductid(String productid);
    Product findByDesignation(String designation);
    void deleteByProductid(String productid);
}
