package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role r where r.user.id=:x")
    public Role roleById(@Param("x") Long id);
 }
