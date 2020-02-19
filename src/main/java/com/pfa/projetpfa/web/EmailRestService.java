package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.*;
import com.pfa.projetpfa.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class EmailRestService {
    @Autowired
    public EmailRepository emailRepository;
    @Autowired
    public ProspectRepository prospectRepository;
    @Autowired
    public ContactRepository contactRepository;
    @RequestMapping(value="/emailes",method= RequestMethod.POST)
    public Email save(@RequestBody Email email)
    {
        Optional<Utilisateur> user=prospectRepository.findById(email.getUtilisateur().getId());
        if(user.isPresent()){
            email.setUtilisateur(user.get());
        }
        return emailRepository.save(email);
    }
    @RequestMapping(value ="/email/{id}",method = RequestMethod.GET)
    public Email roleById(@PathVariable Long id)
    {
        return emailRepository.emailById(id);
    }
    @RequestMapping(value="/emailByMC",method=RequestMethod.GET)
    public Page<Email> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                      @RequestParam(name = "page",defaultValue = "0")int page,
                                      @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return emailRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }
    @RequestMapping(value="/emaile",method= RequestMethod.POST)
    public Email saveEmail(@RequestBody Email email)
    {
        Optional<Contact> user=contactRepository.findById(email.getContact().getId());
        if(user.isPresent()){
            email.setContact(user.get());
        }
        return emailRepository.save(email);
    }
    @RequestMapping(value = "/mesEmails/{id}" , method = RequestMethod.GET)
    public Page<Email> getAllEmails(@PathVariable Long id,
            @RequestParam(name = "page",defaultValue = "0")int page,
                                   @RequestParam(name = "size",defaultValue = "5")int size){
        return emailRepository.getAllEmail(id,PageRequest.of(page,size));
    }
    @RequestMapping(value="/getAllEmailsOrder",method= RequestMethod.GET)
    public Page<Email> getAllEmailsOrder(@RequestParam(name="email",defaultValue = "") String email,
                                         @RequestParam(name = "page",defaultValue = "0")int page,
                                         @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return emailRepository.getMailOrderByUsername(email, PageRequest.of(page,size));
    }
}
