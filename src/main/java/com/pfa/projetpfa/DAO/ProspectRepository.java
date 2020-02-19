package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProspectRepository extends JpaRepository<Utilisateur,Long> {
    @Query("select p from Utilisateur p where p.nom like :x")
    public Page<Utilisateur> chercher(@Param("x") String mc, Pageable pageable);
    @Query("select count(p.email) from Utilisateur p where p.email like :x")
    public Integer countEmail(@Param("x") String email);
    Utilisateur findByUsername(String  email);
    @Query("select p from Utilisateur p where p.username like :x")
    public Utilisateur chercherProspect(@Param("x") String login);
}
