package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.*;
import com.pfa.projetpfa.entities.Contact;
import com.pfa.projetpfa.entities.RDV;
import com.pfa.projetpfa.entities.Role;
import com.pfa.projetpfa.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RdvRestService {
    @Autowired
    public RdvRepository rdvRepository;
    @Autowired
    public ProspectRepository prospectRepository;
    @Autowired
    public ContactRepository contactRepository;
    @Autowired
    public UserRepository userRepository;
    @RequestMapping(value="/saveRdv",method= RequestMethod.POST)
    public RDV save(@RequestBody RDV rdv)
    {
        Optional<Utilisateur> user=prospectRepository.findById(rdv.getUtilisateur().getId());
        if(user.isPresent()){
            rdv.setUtilisateur(user.get());
        }
        return rdvRepository.save(rdv);
    }
    @RequestMapping(value ="/rdv/{id}",method = RequestMethod.GET)
    public Page<RDV> rdvById(@PathVariable Long id ,
                             @RequestParam(name = "page",defaultValue = "0")int page,
                             @RequestParam(name = "size",defaultValue = "5")int size
                             )
    {

        return rdvRepository.rdvById(id,PageRequest.of(page,size));
    }
    @RequestMapping(value ="/rdvContact/{id}",method = RequestMethod.GET)
    public Page<RDV> rdvByIdContact(@PathVariable Long id ,
                             @RequestParam(name = "page",defaultValue = "0")int page,
                             @RequestParam(name = "size",defaultValue = "5")int size
    )
    {

        return rdvRepository.rdvByIdContact(id,PageRequest.of(page,size));
    }
    @RequestMapping(value="/rdvByMC",method=RequestMethod.GET)
    public Page<RDV> chercher(
                                @RequestParam(name = "page",defaultValue = "0")int page,
                                @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return rdvRepository.chercher(PageRequest.of(page,size) );

    }
    @RequestMapping(value="/saveContactRdv",method= RequestMethod.POST)
    public RDV saveRdv(@RequestBody RDV rdv)
    {
        Optional<Contact> user=contactRepository.findById(rdv.getContact().getId());
        if(user.isPresent()){
            rdv.setContact(user.get());
        }
        return rdvRepository.save(rdv);
    }

}
