package Diploma.Controller;

import Diploma.Persistence.Entity.Category;
import Diploma.Persistence.Entity.User;
import Diploma.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SuperController {
    @Autowired
    protected AddressService addressService;
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected RequestService requestService;
    @Autowired
    protected RequestItemService requestItemService;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser(Authentication authentication, HttpServletRequest request) {
        if (authentication != null) {
            User userAuth = (User) authentication.getPrincipal();
            User user = userService.getUser(userAuth.getId());
            }
        return null;
    }
}