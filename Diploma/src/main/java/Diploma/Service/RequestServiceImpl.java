package Diploma.Service;

import Diploma.Persistence.DAO.RequestDAO;
import Diploma.Persistence.Entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestServiceImpl {

    @Autowired
    private RequestDAO requestDAO;

    @Transactional
    public Request getRequest(Long id) {
        return requestDAO.getRequest(id);
    }


    @Transactional
    public Long addRequest(Request request) {
        return requestDAO.addRequest(request);
    }

    @Transactional
    public void updateRequest(Request request) {
        requestDAO.updateRequest(request);
    }

    @Transactional
    public List<Request> requestsInProcessing() {
        return requestDAO.requestsInProcessing();
    }

    public RequestServiceImpl(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }
}