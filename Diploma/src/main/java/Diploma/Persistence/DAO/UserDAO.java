package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.User;

import java.util.List;

public interface UserDAO {
    Long addUser (User user);

    User getUser(Long id);

    void updateUser(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
