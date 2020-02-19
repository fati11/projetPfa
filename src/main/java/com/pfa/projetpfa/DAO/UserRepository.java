package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.User;
import com.pfa.projetpfa.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select p from User p where p.username like :x")
    public User chercherUser(@Param("x") String login);
    User findByUsername(String  email);


}
