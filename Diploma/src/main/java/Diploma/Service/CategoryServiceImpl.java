package Diploma.Service;

import Diploma.Persistence.DAO.CategoryDAO;
import Diploma.Persistence.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryDAO.getCategory(id);
    }
}
