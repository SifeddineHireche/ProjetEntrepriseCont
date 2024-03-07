package com.entreprise.app.rest.Controller;

import com.entreprise.app.rest.ContactService;
import com.entreprise.app.rest.EntrepriseService;
import com.entreprise.app.rest.Model.Contact;
import com.entreprise.app.rest.Model.Entreprise;
import com.entreprise.app.rest.Repository.ContactRepository;
import com.entreprise.app.rest.Repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private EntrepriseService entrepriseService;

    @PostMapping
    public Contact createContact(@RequestBody Contact contact){
        return contactService.createcontact(contact);
    }

    @GetMapping
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();


    }
    @GetMapping("{id}")
    public Contact searchContactById(@PathVariable("id") Long id){
        return contactService.getContactById(id);
    }


    @DeleteMapping("{id}")
    public String deleteContactById(@PathVariable("id") Long id){
        return contactService.deleteContact(id);
    }

    @PutMapping("/{id}")
    public String updateContact(@PathVariable Long id, @RequestBody Contact contact) {

        return contactService.updateContact(id, contact);
    }

    @PostMapping("/{contactId}/add-entreprise/{entrepriseId}")
    public void addContactToEntreprise(@PathVariable Long contactId, @PathVariable Long entrepriseId) {
         entrepriseRepository.addContactToEntreprise(entrepriseId,contactId);
        Contact contact = searchContactById(contactId);
        if(contact!=null){
            contact.setEntreprise_id(entrepriseId);
            updateContact(contactId,contact);
        }
    }
}
