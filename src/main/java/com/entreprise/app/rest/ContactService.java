package com.entreprise.app.rest;

import com.entreprise.app.rest.Exception.EntrepriseNotFoundException;
import com.entreprise.app.rest.Exception.ResourceNotFoundException;
import com.entreprise.app.rest.Model.Contact;
import com.entreprise.app.rest.Model.Entreprise;
import com.entreprise.app.rest.Model.EntrepriseContact;
import com.entreprise.app.rest.Repository.ContactRepository;
import com.entreprise.app.rest.Repository.EntrepriseContactRepository;
import com.entreprise.app.rest.Repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public Contact createcontact(Contact contact){

        //chaque contact doit etre associe au moins a une entreprise
        if(!contact.getEntreprises().isEmpty()){

            for(Entreprise entrepriseAssocie: contact.getEntreprises()){
                Contact  contactModifie  = alimenterContact(contact);
                Contact contactCree2 = contactRepository.save(contactModifie);
                entrepriseRepository.addContactToEntreprise(entrepriseAssocie.getId(),contactCree2.getId());
                contactModifie.setContact_id(contactCree2.getId());
                contactModifie.setEntreprise_id(entrepriseAssocie.getId());
                updateContact(contactModifie.getId(),contactModifie);

            }

        }else{
            throw new EntrepriseNotFoundException("Pas d'entreprise associe au contact");
        }
        return  contact;
    }

    public Contact alimenterContact(Contact contact) {
        Contact contactModifie = new Contact();
        contactModifie.setFirstName(contact.getFirstName());
        contactModifie.setLastName(contact.getLastName());
        contactModifie.setAddress(contact.getAddress());
        contactModifie.setFreelance(contact.isFreelance());

        if (contactModifie.isFreelance() && contactModifie.getVatNumber() != null) {
            contactModifie.setVatNumber(contact.getVatNumber());
        }
        if (contactModifie.isFreelance() && (contactModifie.getVatNumber() == null || contactModifie.getVatNumber().isBlank())) {
            return null;
        }
        return contactModifie;
    }

    public List<Contact> getAllContacts(){
        List <Contact>list= contactRepository.findAll();
         return list;
    }


    public Contact getContactById(Long id){
        Optional<Contact> optionalContact = contactRepository.findById(id);

        return optionalContact.get();
    }

    public String deleteContact(Long id){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));

        contactRepository.delete(contact);
        return "Le contact "+ contact.getFirstName() +" avec id "+contact.getId()+" a été supprimé";
    }
    public String updateContact(Long id, Contact contactRequest) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));

        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());
        contact.setAddress(contactRequest.getAddress());
        contact.setFreelance(contactRequest.isFreelance());

        if (contactRequest.isFreelance()) {
            contact.setVatNumber(contactRequest.getVatNumber());
        } else {
            contact.setVatNumber(null); // Assurez-vous que le numéro de TVA est null si le contact n'est pas freelance
        }

        contactRepository.save(contact);
        return  "Le contact "+ contact.getFirstName() +" avec id "+contact.getId()+" a été modifié";
    }


}
