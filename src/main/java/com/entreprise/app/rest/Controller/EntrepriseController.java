package com.entreprise.app.rest.Controller;

import com.entreprise.app.rest.ContactService;
import com.entreprise.app.rest.EntrepriseService;
import com.entreprise.app.rest.Model.Contact;
import com.entreprise.app.rest.Model.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    @GetMapping
    public List<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }

    @PostMapping
    public Entreprise createEntreprise(@RequestBody Entreprise entreprise) {
        return entrepriseService.createEntreprise(entreprise);
    }

    @PutMapping("/{id}")
    public String updateEntreprise(@PathVariable Long id, @RequestBody Entreprise entrepriseDetails) {
       return  entrepriseService.updateEntreprise(id,entrepriseDetails);
    }

    @GetMapping("/search")
    public Entreprise searchEntrepriseByVatNumber(@RequestParam String tvaNumber) {
        return entrepriseService.searchEntrepriseByVatNumber(tvaNumber);
    }



}
