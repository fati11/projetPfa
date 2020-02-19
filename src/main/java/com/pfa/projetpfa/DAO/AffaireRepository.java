package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.Affaire;
import com.pfa.projetpfa.entities.RDV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AffaireRepository extends JpaRepository<Affaire,Long> {
    @Query("select a from Affaire a where a.contact.id=:x")
    public Affaire affaireById(@Param("x") Long id);
    @Query("select a from Affaire a where a.contact.nom like :x")
    public Page<Affaire> chercher(@Param("x") String mc, Pageable pageable);
    @Query("select  a from Affaire a where  a.contact.id=:x ")
    public Page<Affaire> getAllAffaires(@Param("x") Long id,Pageable pageable);
}
