package com.entreprise.app.rest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "entreprises")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String address;
    @NotBlank
    private String tvaNumber;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "entreprise_contact",
            joinColumns = @JoinColumn(name = "entreprise_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id"))
    @JsonIgnore
    private Set<Contact> contacts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTvaNumber() {
        return tvaNumber;
    }

    public void setTvaNumber(String tvaNumber) {
        this.tvaNumber = tvaNumber;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
