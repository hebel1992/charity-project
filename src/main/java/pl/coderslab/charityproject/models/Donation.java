package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charityproject.validationGroups.UserCreatingDonation;
import pl.coderslab.charityproject.validators.MyTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations")
public class Donation extends AbstractEntity {

    @NotNull(groups = {Default.class, UserCreatingDonation.class})
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull(groups = {Default.class, UserCreatingDonation.class})
    @Size(min = 3, message = "min. 3 znaki", groups = {Default.class, UserCreatingDonation.class})
    @Column(name = "street")
    private String street;

    @NotNull(groups = {Default.class, UserCreatingDonation.class})
    @Size(min = 3, message = "min. 3 znaki", groups = {Default.class, UserCreatingDonation.class})
    @Column(name = "city")
    private String city;

    @NotNull(groups = {Default.class, UserCreatingDonation.class})
    @Pattern(regexp="[\\d]{8,10}", message = "8-10 cyfr", groups = {Default.class, UserCreatingDonation.class})
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(groups = {Default.class, UserCreatingDonation.class})
    @Pattern(regexp="[\\d]{2}-[\\d]{3}", message = "niepoprawny format", groups = {Default.class, UserCreatingDonation.class})
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull(groups = {Default.class, UserCreatingDonation.class})
    @Future(message = "musi być data z przyszłości", groups = {Default.class, UserCreatingDonation.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pick_up_date")
    private LocalDate pickUpDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "actual_pick_up_date")
    private LocalDate actualPickUpDate;

    @NotNull(groups = {Default.class, UserCreatingDonation.class})
    @MyTime(minHour = 8, maxHour = 20, groups = {Default.class, UserCreatingDonation.class})
    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;

    @Column(name = "pick_up_comment")
    private String pickUpComment;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "status")
    private Status status;

    @NotNull(groups = {UserCreatingDonation.class})
    @Size(min = 1, message = "nie podano kategorii", groups = {UserCreatingDonation.class})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "donations_categories", joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @NotNull(message = "nie zaznaczono instytucji")
    @JoinColumn(name = "institution_id")
    @ManyToOne
    private Institution institution;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @PrePersist
    public void create(){
        this.created = LocalDate.now();
    }
}
