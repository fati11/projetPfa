package com.pfa.projetpfa.DAO;

import com.pfa.projetpfa.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
