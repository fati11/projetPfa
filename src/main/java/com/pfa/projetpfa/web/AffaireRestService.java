package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.AffaireRepository;
import com.pfa.projetpfa.DAO.ContactRepository;
import com.pfa.projetpfa.DAO.Email;
import com.pfa.projetpfa.entities.Affaire;
import com.pfa.projetpfa.entities.Contact;
import com.pfa.projetpfa.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AffaireRestService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AffaireRepository affaireRepository;
    @RequestMapping(value="/saveAffaire",method= RequestMethod.POST)
    public Affaire save(@RequestBody Affaire affaire)
    {
        Optional<Contact> user=contactRepository.findById(affaire.getContact().getId());
        if(user.isPresent()){
            affaire.setContact(user.get());
        }
        return affaireRepository.save(affaire);
    }
    @RequestMapping(value ="/affaire/{id}",method = RequestMethod.GET)
    public Affaire affaireById(@PathVariable Long id)
    {
        return affaireRepository.affaireById(id);
    }
    @RequestMapping(value="/affaireByMC",method=RequestMethod.GET)
    public Page<Affaire> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                @RequestParam(name = "page",defaultValue = "0")int page,
                                @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return affaireRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }
    @RequestMapping(value = "/mesAffaires/{id}" , method = RequestMethod.GET)
    public Page<Affaire> getAllAffaires(@PathVariable Long id,
                                    @RequestParam(name = "page",defaultValue = "0")int page,
                                    @RequestParam(name = "size",defaultValue = "5")int size){
        return affaireRepository.getAllAffaires(id,PageRequest.of(page,size));

    }
}
