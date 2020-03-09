package pl.coderslab.charityproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charityproject.models.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
