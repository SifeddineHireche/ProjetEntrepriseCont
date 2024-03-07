package com.entreprise.app.rest;

import com.entreprise.app.rest.Exception.ResourceNotFoundException;
import com.entreprise.app.rest.Model.Contact;
import com.entreprise.app.rest.Model.Entreprise;
import com.entreprise.app.rest.Model.EntrepriseContact;
import com.entreprise.app.rest.Repository.ContactRepository;
import com.entreprise.app.rest.Repository.EntrepriseContactRepository;
import com.entreprise.app.rest.Repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Component
public class EntrepriseService {
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private EntrepriseContactRepository entrepriseContactRepository;


    @Autowired
    private ContactRepository contactRepository;

    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    public Entreprise createEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);

    }

    public String updateEntreprise(Long id, Entreprise entreprise) {
        entreprise.setId(id); // Assurez-vous que l'entreprise a l'ID correct pour la mise à jour
        entrepriseRepository.save(entreprise);
        return  "L'entreprise "+ entreprise.getName() +" avec id "+entreprise.getId()+" a été modifié";
    }

    public Entreprise searchEntrepriseByVatNumber(String vatNumber) {
        return entrepriseRepository.findByTvaNumber(vatNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with VAT number: " + vatNumber));
    }

    public void addContactToEntreprise(Long entrepriseId, Long contactId) {



        Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with id: " + entrepriseId));


        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));



        Set<Entreprise> entreprises =  new HashSet<>();
        entreprises.add(entreprise);
        contact.setEntreprises(entreprises);
        // Maintenant, enregistrez la relation entreprise_contact

        EntrepriseContact entrepriseContact = new EntrepriseContact(entreprise, contact);
        //entreprise.getContacts().add(contact);
        entrepriseContact.setId(entrepriseId);



        if (!entrepriseContactRepository.existsByContactIdAndEntrepriseId(contactId, entrepriseId)) {
            entrepriseContactRepository.save(entrepriseContact);
            entrepriseRepository.flush();
       }


    }

}
