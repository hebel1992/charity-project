package pl.coderslab.charityproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charityproject.models.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
