package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.Role;
import com.pfa.projetpfa.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailRepository extends JpaRepository<Email,Long> {
    @Query("select e from Email e where e.utilisateur.id=:x")
    public Email emailById(@Param("x") Long id);
    @Query("select e from Email e where e.contact.id=:x")
    public Email emailByIdContact(@Param("x") Long id);
    @Query("select e from Email e where e.email like :x")
    public Page<Email> chercher(@Param("x") String mc, Pageable pageable);
    @Query("select  e from Email e where  e.utilisateur.id=:x or e.contact.id=:x order by e.date desc ")
    public Page<Email> getAllEmail(@Param("x") Long id,Pageable pageable);
    @Query("select e from Email e where e.contact.username=:x or e.utilisateur.username=:x")
    public Page<Email> getMailOrderByUsername(@Param("x") String email, Pageable pageable);
}
