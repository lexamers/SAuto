package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories();

    Category getCategory(Long id);
}
