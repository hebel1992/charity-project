package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.charityproject.validationGroups.EditedUser;
import pl.coderslab.charityproject.validationGroups.UserChangePassword;
import pl.coderslab.charityproject.validators.UniqueEmail;
import pl.coderslab.charityproject.validators.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
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

    @NotNull(groups = {Default.class, EditedUser.class})
    @Size(min = 3, max = 15, message = "3-15 znaków", groups = {Default.class, EditedUser.class})
    @Column(name = "first_name")
    private String firstName;

    @NotNull(groups = {Default.class, EditedUser.class})
    @Size(min = 2, max = 15, message = "2-15 znaków", groups = {Default.class, EditedUser.class})
    @Column(name = "last_name")
    private String lastName;

    @NotNull(groups = {Default.class, EditedUser.class})
    @NotBlank(message = "pole nie może być puste", groups = {Default.class, EditedUser.class})
    @Email(groups = {Default.class, EditedUser.class})
    @UniqueEmail
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(groups = {Default.class, EditedUser.class})
    @UniqueUsername
    @Size(min = 8, max = 15, message = "8-15 znaków", groups = {Default.class, EditedUser.class})
    @Column(name = "username", unique = true)
    private String username;

    @NotNull(groups = {Default.class, UserChangePassword.class})
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\=])(?=.*[A-Z])(?!.*\\s).{8,}$",
            message = "min. 8 znaków, musi zawierać: małą litere, dużą litere, cyfrę, znak specjalny [!@#$%^&*()_+-=]",
            groups = {Default.class, UserChangePassword.class})
    @Column(name = "password")
    private String password;

    @Column(name = "blocked")
    private Boolean blocked;

    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Donation> donations;
}
