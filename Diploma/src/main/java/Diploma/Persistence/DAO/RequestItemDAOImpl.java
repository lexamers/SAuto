package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Request;
import Diploma.Persistence.Entity.RequestItem;
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
public class RequestItemDAOImpl implements RequestItemDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRequestItem(RequestItem requestItem) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(requestItem);
    }

    @Override
    public RequestItem getByRequest(Request request) {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery<RequestItem> cr = cb.createQuery(RequestItem.class);
        Root<RequestItem> root = cr.from(RequestItem.class);

        Predicate sameRequest = cb.equal(root.get("request"), request);
        cr.select(root).where(cb.and(sameRequest));
        Query<RequestItem> query = currentSession.createQuery(cr);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void removeRequestItem(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.clear();
        RequestItem requestItem = new RequestItem();
        requestItem.setId(id);
        currentSession.delete(requestItem);
    }

    @Override
    public RequestItem getRequestItem(Long id) {
        return null;
    }

    @Override
    public RequestItem getOrderItem(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(RequestItem.class, id);
    }
}
