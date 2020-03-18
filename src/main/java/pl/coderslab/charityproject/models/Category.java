package pl.coderslab.charityproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {
    @Getter
    @Setter
    @NotNull
    @Size(min = 3, max = 20, message = "min. 3, max. 20 znaków")
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Donation> donations;

    @PreRemove
    private void preRemove(){
        for(Donation d: donations){
            d.getCategories().remove(this);
        }
    }
}
