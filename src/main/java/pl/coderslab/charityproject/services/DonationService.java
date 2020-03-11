package pl.coderslab.charityproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charityproject.models.Donation;
import pl.coderslab.charityproject.repositories.DonationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;

    public void saveDonation(Donation donation){
        donationRepository.save(donation);
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public Integer getAllBags() {
        int count = 0;
        for (Donation d : findAll()) {
            count += d.getQuantity();
        }
        return count;
    }
}
