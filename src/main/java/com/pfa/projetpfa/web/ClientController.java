package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.ClientRepository;
import com.pfa.projetpfa.entities.Client;
import com.pfa.projetpfa.entities.Contact;
import com.pfa.projetpfa.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @RequestMapping(value="/saveClient",method= RequestMethod.POST)
    public Client save(@RequestBody Client client)
    {
        client.setPassword(bcryptEncoder.encode(client.getPassword()));
        client.setUsername(client.getEmail());
        Role r=new Role();
        r.setUser(client);
        r.setRoleName("client");
        return clientRepository.save(client);
    }
}
