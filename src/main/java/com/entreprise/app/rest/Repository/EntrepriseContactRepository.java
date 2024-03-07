package com.entreprise.app.rest.Repository;

import com.entreprise.app.rest.Model.EntrepriseContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseContactRepository extends JpaRepository<EntrepriseContact, Long> {
    boolean existsByContactIdAndEntrepriseId(Long contactId, Long entrepriseId);
}