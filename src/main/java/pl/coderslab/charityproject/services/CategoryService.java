package pl.coderslab.charityproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charityproject.EntityNotFoundException;
import pl.coderslab.charityproject.models.Category;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id, User.class.getSimpleName()));
    }

    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
