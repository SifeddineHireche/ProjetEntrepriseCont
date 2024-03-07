package com.entreprise.app.rest.Repository;

import com.entreprise.app.rest.Model.Contact;
import com.entreprise.app.rest.Model.Entreprise;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ComponentScan
public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
    Optional<Entreprise> findByTvaNumber(String vatNumber);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO entreprise_contact (entreprise_id, contact_id) VALUES (:entrepriseId, :contactId)", nativeQuery = true)
    void addContactToEntreprise(Long entrepriseId, Long contactId);


}
