package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Address;
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

@Repository
public class AddressDAOImpl implements AddressDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addAddress(Address address) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Long) currentSession.save(address);
    }

    @Override
    public Address getLastUserAddress(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery <Address> cr = cb.createQuery(Address.class);
        Root <Address> root = cr.from(Address.class);

        Predicate sameUser = cb.equal(root.get("user"), user);
        cr.select(root).where(sameUser).orderBy(cb.desc(root.get("id")));
        Query <Address>query = currentSession.createQuery(cr);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void updateAddress(Address address) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(address);
    }
}
