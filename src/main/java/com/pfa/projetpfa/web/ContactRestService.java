package com.pfa.projetpfa.web;

import com.pfa.projetpfa.DAO.ContactRepository;
import com.pfa.projetpfa.DAO.ProspectRepository;
import com.pfa.projetpfa.entities.Contact;
import com.pfa.projetpfa.entities.Role;
import com.pfa.projetpfa.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContactRestService {
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    ContactRepository contactRepository;
    @RequestMapping(value="/contacts" ,method= RequestMethod.GET)
    public List<Contact> getContacts()
    {
        return contactRepository.findAll();
    }
    @RequestMapping(value="/contact/{id}",method=RequestMethod.GET)
    public Optional<Contact> getEmploye(@PathVariable Long id)
    {
        return contactRepository.findById(id);
    }
    @RequestMapping(value="/saveContact",method=RequestMethod.POST)
    public Contact save(@RequestBody Contact contact)
    {
        contact.setPassword(bcryptEncoder.encode(contact.getPassword()));
        contact.setUsername(contact.getEmail());
        Role r=new Role();
        r.setUser(contact);
        r.setRoleName("contact");
        return contactRepository.save(contact);
    }
    @RequestMapping(value="/contactByMC",method=RequestMethod.GET)
    public Page<Contact> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                      @RequestParam(name = "page",defaultValue = "0")int page,
                                      @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return contactRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }
    @RequestMapping(value ="/contact/{id}",method = RequestMethod.DELETE)
    public boolean supprimer(@PathVariable Long id){
        contactRepository.deleteById(id);
        return true;
    }
    @RequestMapping(value = "/countMailContact" , method = RequestMethod.GET)
    public Integer countEmail(@RequestParam(name = "email",defaultValue = "")String email){
        return contactRepository.countEmail(email);
    }
    @RequestMapping(value="/contactG/{id}",method=RequestMethod.GET)
    public Optional<Contact> getContact(@PathVariable Long id)
    {
        return contactRepository.findById(id);
    }
    @RequestMapping(value="/updateContact/{id}",method=RequestMethod.PUT)
    public Contact updateContact(@PathVariable Long id,@RequestBody Contact contact)
    {
        contact.setId(id);
        return contactRepository.save(contact);
    }
    @RequestMapping(value="/chercherContact",method=RequestMethod.GET)
    public Contact chercher(@RequestParam(name = "username",defaultValue = "") String nom
    )
    {
        return contactRepository.chercherContact(nom);

    }
}
