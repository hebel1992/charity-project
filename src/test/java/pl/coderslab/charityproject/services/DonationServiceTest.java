package pl.coderslab.charityproject.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charityproject.models.Category;
import pl.coderslab.charityproject.models.Donation;
import pl.coderslab.charityproject.models.Institution;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
class DonationServiceTest {

    @Autowired
    DonationService donationService;

    @Autowired
    InstitutionService institutionService;

    @Autowired
    CategoryService categoryService;

    @Test
    void shouldSaveDonationsThenReturnProperValues() {

        //GIVEN

        Institution institution = new Institution();
        institution.setName("Inst1");
        institution.setDescription("Description1");
        institution.setCity("Szczecin");
        institution.setPhoneNumber("3456513122");

        institutionService.saveInstitution(institution);

        Category category = new Category();
        category.setName("ubrania");
        Category category2 = new Category();
        category2.setName("buty");

        categoryService.saveCategory(category);
        categoryService.saveCategory(category2);

        Donation donation1 = new Donation();
        donation1.setQuantity(3);
        donation1.setStreet("Mala 5");
        donation1.setCity("Warszawa");
        donation1.setPhoneNumber("456999234");
        donation1.setZipCode("45-200");
        donation1.setPickUpDate(LocalDate.now());
        donation1.setPickUpTime(LocalTime.now());
        donation1.setInstitution(institution);
        donation1.setCategories(new HashSet<>(Arrays.asList(category)));

        Donation donation2 = new Donation();
        donation2.setQuantity(6);
        donation2.setStreet("Mala 12");
        donation2.setCity("Warszawa");
        donation2.setPhoneNumber("123451123");
        donation2.setZipCode("45-200");
        donation2.setPickUpDate(LocalDate.now());
        donation2.setPickUpTime(LocalTime.now());
        donation2.setInstitution(institution);
        donation2.setCategories(new HashSet<>(Arrays.asList(category, category2)));

        //WHEN

        donationService.saveDonation(donation1);
        donationService.saveDonation(donation2);

        int countBags = 0;
        for (Donation d : donationService.findAll()) {
            countBags += d.getQuantity();
        }

        //THEN

        Assertions.assertThat(countBags).isEqualTo(9);
        Assertions.assertThat(donation1.getCategories()).doesNotContain(category2);
        Assertions.assertThat(donation2.getCategories().size()).isEqualTo(2);
        Assertions.assertThat(donation1.getInstitution()).isNotNull();
    }


}
