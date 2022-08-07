package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Long) currentSession.save(user);
    }

    @Override
    public User getUser(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (User) currentSession.get(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(user);
    }

    @Override
    public User getUserByEmail(String email) {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        Predicate sameEmail = cb.equal(root.get("email"), email);
        cr.select(root).where(sameEmail);
        Query<User> query = currentSession.createQuery(cr);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery <User> cq = cb.createQuery(User.class);
        Root <User> root = cq.from(User.class);
        cq.select(root);
        javax.persistence.Query query = (javax.persistence.Query) session.createQuery(cq);
        return query.getResultList();
    }
}
