package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "institutions")
public class Institution extends AbstractEntity {
    @NotNull
    @Size(min = 3, message = "min 3 znaki")
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 10, message = "min 10 characters")
    @Column(name = "description")
    private String description;

    @NotNull
    @Size(min = 3, message = "min 3 znaki")
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 8, max = 10, message = "min 8, max 10 znaków")
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.REMOVE)
    private List<Donation> donations;
}
