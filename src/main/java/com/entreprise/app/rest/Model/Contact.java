package com.entreprise.app.rest.Model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;

    private boolean freelance=false;

    private Long entreprise_id;

    private  Long contact_id;
    private String vatNumber; // Champ supplémentaire pour le numéro de TVA si le contact est freelance

    @ManyToMany(mappedBy = "contacts",fetch = FetchType.LAZY)
    private Set<Entreprise> entreprises = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFreelance() {
        return freelance;
    }

    public void setFreelance(boolean freelance) {
        this.freelance = freelance;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Set<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Set<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }

    public Long getEntreprise_id() {
        return entreprise_id;
    }

    public void setEntreprise_id(Long entreprise_id) {
        this.entreprise_id = entreprise_id;
    }

    public Long getContact_id() {
        return contact_id;
    }

    public void setContact_id(Long contact_id) {
        this.contact_id = contact_id;
    }
}
