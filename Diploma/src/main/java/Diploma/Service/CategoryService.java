package Diploma.Service;

import Diploma.Persistence.Entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    Category getCategory(Long id);

}
