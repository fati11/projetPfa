package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.*;
import com.pfa.projetpfa.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
public class RoleRestService {
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public ProspectRepository prospectRepository;
    @Autowired
    public ContactRepository contactRepository;
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    public UserRepository userRepository;
    @RequestMapping(value="/roles",method= RequestMethod.POST)
    public Utilisateur saveRoleProspect(@RequestBody Utilisateur c)
    {
        c.setDateCreation(new Date());
        c.setPassword(bcryptEncoder.encode(c.getPassword()));
        c.setUsername(c.getEmail());
        Role x=new Role();
        x.setRoleName("prospect");
        x.setUser(c);
        c.setRole(x);
        return prospectRepository.save(c);
    }
    @RequestMapping(value="/saveRoles",method= RequestMethod.POST)
    public Contact saveRoleContact(@RequestBody Contact c)
    {
        c.setDateCreation(new Date());
        c.setPassword(bcryptEncoder.encode(c.getPassword()));
        c.setUsername(c.getEmail());
        Role x=new Role();
        x.setRoleName("contact");
        x.setUser(c);
        c.setRole(x);
        return contactRepository.save(c);
    }
    @RequestMapping(value="/saveRoleClient",method= RequestMethod.POST)
    public Client saveRoleClient(@RequestBody Client c)
    {
        c.setDateCreation(new Date());
        c.setPassword(bcryptEncoder.encode(c.getPassword()));
        c.setUsername(c.getEmail());
        Role x=new Role();
        x.setRoleName("client");
        x.setUser(c);
        c.setRole(x);
        return clientRepository.save(c);
    }
    @RequestMapping(value ="/role/{id}",method = RequestMethod.GET)
    public Role roleById(@PathVariable Long id)
    {
       return roleRepository.roleById(id);
    }

    @RequestMapping(value="/roless" ,method= RequestMethod.GET)
    public List<Role> getroles()
    {
        return roleRepository.findAll();
    }
}
