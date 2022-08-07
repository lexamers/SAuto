package Diploma.Service;

import Diploma.Persistence.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Long addUser (User user);

    User getUser(Long id);

    void updateUser(User user);

    List<User> getAllUsers();
}
