package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRole(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Role.class, id);
    }
}

