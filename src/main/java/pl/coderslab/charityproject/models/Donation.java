package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
    @Size(min = 8, max = 10, message = "min 8, max 10 znaków")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Size(min = 3, message = "min 5 znaków")
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pick_up_date")
    private LocalDate pickUpDate;

    @NotNull
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
}
