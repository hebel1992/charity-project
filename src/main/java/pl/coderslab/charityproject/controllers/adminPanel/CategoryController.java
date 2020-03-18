package pl.coderslab.charityproject.controllers.adminPanel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.Category;
import pl.coderslab.charityproject.services.CategoryService;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController extends AbstractController {

    private final CategoryService categoryService;

    public CategoryController(DonationService donationService, CategoryService categoryService,
                              UserService userService, InstitutionService institutionService) {
        super(donationService, userService, institutionService);
        this.categoryService = categoryService;
    }

    @RequestMapping("/categories")
    public String categoriesList(Model model) {
        model.addAttribute("category", new Category());
        return "admin-categories/categories-manager";
    }

    @RequestMapping("/delete-category/{categoryId}")
    public String deleteCategoryAction(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        categoryService.deleteCategory(category);

        return "redirect:/admin/categories";
    }

    @PostMapping("/add-category-action")
    public String addCategoryAction(@ModelAttribute("category") @Valid Category category,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-categories/categories-manager";
        }

        categoryService.saveCategory(category);

        return "redirect:/admin/categories";
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }
}
