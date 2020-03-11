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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations")
public class Donation extends AbstractEntity {

    @NotNull(message = "pole nie może być puste")
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull(message = "pole nie może być puste")
    @Size(min = 5, message = "min 5 znaków")
    @Column(name = "street")
    private String street;

    @NotNull(message = "pole nie może być puste")
    @Size(min = 3, message = "min 3 znaki")
    @Column(name = "city")
    private String city;

    @NotNull(message = "pole nie może być puste")
    @Size(min = 8, max = 10, message = "min 8, max 10 znaków")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "pole nie może być puste")
    @Size(min = 3, message = "min 5 znaków")
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull(message = "pole nie może być puste")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pick_up_date")
    private LocalDate pickUpDate;

    @NotNull(message = "pole nie może być puste")
    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;

    @Column(name = "pick_up_comment")
    private String pickUpComment;

    @Size(min = 1, message = "nie podano kategorii")
    @ManyToMany
    @JoinTable(name = "donations_categories", joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @NotNull(message = "nie zaznaczono instytucji")
    @ManyToOne
    private Institution institution;
}
