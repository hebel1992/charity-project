package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "institutions")
public class Institution extends AbstractEntity {
    @NotNull
    @Size(min = 3, message = "min 3 characters")
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 10, message = "min 10 characters")
    @Column(name = "description")
    private String description;
}
