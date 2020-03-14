package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.charityproject.validators.UniqueEmail;
import pl.coderslab.charityproject.validators.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 15, message = "min 3, max 15 znaków")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 15, message = "min 2, max 15 znaków")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotBlank(message = "pole nie może być puste")
    @Email
    @UniqueEmail
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @UniqueUsername
    @Size(min = 8, max = 15, message = "min 8, max 15 znaków")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Size(min = 8, message = "min 8 znaków")
    @Column(name = "password")
    private String password;

    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Donation> donations;
}
