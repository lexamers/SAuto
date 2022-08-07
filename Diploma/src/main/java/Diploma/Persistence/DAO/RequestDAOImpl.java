package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Request;
import Diploma.Persistence.Entity.User;
import Diploma.Service.RequestStatus;
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
import org.springframework.stereotype.Repository;

@Repository
public class RequestDAOImpl implements RequestDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Request getRequest(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Request.class, id);
    }

    @Override
    public Long addRequest(Request request) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Long) currentSession.save(request);
    }

    @Override
    public void updateRequest(Request request) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(request);
    }

    @Override
    public Request getLastUserRequest(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery<Request> cr = cb.createQuery(Request.class);
        Root<Request> root = cr.from(Request.class);

        Predicate sameUser = cb.equal(root.get("user"), user);
        Predicate sameStatus = cb.equal(root.get("status"), RequestStatus.Formed);
        cr.select(root).where(cb.and(sameUser, sameStatus)).orderBy(cb.desc(root.get("id")));
        Query<Request> query = currentSession.createQuery(cr);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<Request> requestsInProcessing() {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery<Request> cr = cb.createQuery(Request.class);
        Root<Request> root = cr.from(Request.class);

        Predicate sameStatusSent = cb.equal(root.get("status"), RequestStatus.Sent);
        Predicate sameStatusInProcessing = cb.equal(root.get("status"), RequestStatus.InProcessing);
        Predicate sameStatusReceived = cb.equal(root.get("status"), RequestStatus.Received);
        cr.select(root).where(cb.or(sameStatusSent, sameStatusInProcessing, sameStatusReceived)).requestBy(cb.desc(root.get("id")));
        Query<Request> query = currentSession.createQuery(cr);
        return query.getResultList();
    }
}

