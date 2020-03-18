package pl.coderslab.charityproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charityproject.EntityNotFoundException;
import pl.coderslab.charityproject.models.Donation;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.repositories.DonationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;

    public void saveDonation(Donation donation){
        donationRepository.save(donation);
    }

    public void deleteDonation(Donation donation){
        donationRepository.delete(donation);
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public Donation findById(Long id){
        return donationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, User.class.getSimpleName()));
    }

    public List<Donation> findByUser(Long id){
        return donationRepository.findByUser_id(id);
    }

    public Integer getAllBags() {
        int count = 0;
        for (Donation d : findAll()) {
            count += d.getQuantity();
        }
        return count;
    }
}
