package pl.coderslab.charityproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.repositories.InstitutionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    public void saveInstitution(Institution institution){
        institutionRepository.save(institution);
    }
}
