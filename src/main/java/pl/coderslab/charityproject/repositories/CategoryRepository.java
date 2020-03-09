package pl.coderslab.charityproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charityproject.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
