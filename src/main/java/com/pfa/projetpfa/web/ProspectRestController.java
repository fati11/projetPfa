package com.pfa.projetpfa.web;
import com.pfa.projetpfa.DAO.Email;
import com.pfa.projetpfa.DAO.ProspectRepository;
import com.pfa.projetpfa.entities.Role;
import com.pfa.projetpfa.entities.User;
import com.pfa.projetpfa.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.Optional;

@RestController
public class ProspectRestController {
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    ProspectRepository prospectRepository;
    @RequestMapping(value="/prospects" ,method= RequestMethod.GET)
    public List<Utilisateur> getProspects()
    {
        return prospectRepository.findAll();
    }
    @RequestMapping(value="/prospect/{id}",method=RequestMethod.GET)
    public Optional<Utilisateur> getEmploye(@PathVariable Long id)
    {
        return prospectRepository.findById(id);
    }
    @RequestMapping(value="/saveProspect",method=RequestMethod.POST)
    public Utilisateur save(@RequestBody Utilisateur prospect)
    {
        prospect.setPassword(bcryptEncoder.encode(prospect.getPassword()));
        prospect.setUsername(prospect.getEmail());
        Role r=new Role();
        r.setUser(prospect);
        r.setRoleName("prospect");
        return prospectRepository.save(prospect);
    }
    @RequestMapping(value="/prospectByMC",method=RequestMethod.GET)
    public Page<Utilisateur> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                   @RequestParam(name = "page",defaultValue = "0")int page,
                                   @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return prospectRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }
    @RequestMapping(value ="/prospect/{id}",method = RequestMethod.DELETE)
    public boolean supprimer(@PathVariable Long id){
        prospectRepository.deleteById(id);
        return true;
    }
    @RequestMapping(value = "/countMail" , method = RequestMethod.GET)
    public Integer countEmail(@RequestParam(name = "email",defaultValue = "")String email){
        return prospectRepository.countEmail(email);
    }
    @RequestMapping(value="/prospectG/{id}",method=RequestMethod.GET)
    public Optional<Utilisateur> getProspect(@PathVariable Long id)
    {
        return prospectRepository.findById(id);
    }
    @RequestMapping(value="/updateProspect/{id}",method=RequestMethod.PUT)
    public Utilisateur updateProspect(@PathVariable Long id,@RequestBody Utilisateur prospect)
    {
        prospect.setId(id);
        return prospectRepository.save(prospect);
    }
    @RequestMapping(value="/chercherProspect",method=RequestMethod.GET)
    public Utilisateur chercher(@RequestParam(name = "username",defaultValue = "") String nom
    )
    {
        return prospectRepository.chercherProspect(nom);

    }

}
