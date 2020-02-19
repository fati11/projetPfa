package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.RDV;
import com.pfa.projetpfa.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RdvRepository extends JpaRepository<RDV,Long> {
    @Query("select r from RDV r where r.utilisateur.id=:x or r.contact.id=:x")
    public Page<RDV> rdvById(@Param("x") Long id,Pageable pageable );
    @Query("select r from RDV r where r.contact.id=:x")
    public Page<RDV> rdvByIdContact(@Param("x") Long id,Pageable pageable );
    @Query("select r from RDV r")
    public Page<RDV> chercher(Pageable pageable);
}
