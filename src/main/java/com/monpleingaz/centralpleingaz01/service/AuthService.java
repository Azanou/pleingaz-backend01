package com.monpleingaz.centralpleingaz01.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monpleingaz.centralpleingaz01.model.Agence;
import com.monpleingaz.centralpleingaz01.model.Employer;
import com.monpleingaz.centralpleingaz01.repository.AgenceRepository;
import com.monpleingaz.centralpleingaz01.repository.EmployerRepository;

@Service
public class AuthService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private AgenceRepository agenceRepository;

    public void synchronizeEmployer(String uuid, Map<String, Object> claims) {
        Optional<Employer> existingEmployer = employerRepository.findByUuidEmployer(uuid);

        if (existingEmployer.isPresent()) {
            return;
        }

        Employer newEmployer = new Employer();
        newEmployer.setUuidEmployer(uuid);

        String nom = (String) claims.get("username");
        String email = (String) claims.get("email");
        String agenceName = (String) claims.get("agence");

        if(agenceName != null){
            Agence agence = agenceRepository.findByNomAgence(agenceName).orElseThrow(()->new RuntimeException("Agence non trouvee dans la base de donnees"));
            newEmployer.setAgence(agence);
        }
        
        newEmployer.setNomEmployer(nom);
        newEmployer.setEmailEmployer(email);
        employerRepository.save(newEmployer);
    }
}