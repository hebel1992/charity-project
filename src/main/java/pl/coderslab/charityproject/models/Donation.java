package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charityproject.validators.MyTime;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @Size(min = 3, message = "min 3 znaki")
    @Column(name = "street")
    private String street;

    @NotNull
    @Size(min = 3, message = "min 3 znaki")
    @Column(name = "city")
    private String city;

    @NotNull
    @Pattern(regexp="[\\d]{8,10}", message = "min 8, max 10 cyfr")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp="[\\d]{2}-[\\d]{3}", message = "niepoprawny format")
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @Future(message = "musi być data z przyszłości")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pick_up_date")
    private LocalDate pickUpDate;

    @NotNull
    @MyTime(minHour = 8, maxHour = 20)
    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;

    @Column(name = "pick_up_comment")
    private String pickUpComment;

    @NotNull
    @Size(min = 1, message = "nie podano kategorii")
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
}
