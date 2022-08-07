package Diploma.Service;

import Diploma.Persistence.DAO.RequestItemDAO;
import Diploma.Persistence.Entity.Request;
import Diploma.Persistence.Entity.RequestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestItemServiceImpl implements RequestItemService {

    @Autowired
    private RequestItemDAO requestItemDAO;

    @Override
    @Transactional
    public void addRequestItem(RequestItem requestItem) {
        requestItemDAO.addRequestItem(requestItem);
    }

    @Override
    @Transactional
    public RequestItem getByRequest(Request request) {
        return requestItemDAO.getByRequest(request);
    }

    @Override
    @Transactional
    public void removeRequestItem(Long id) {
        requestItemDAO.removeRequestItem(id);
    }

    public RequestItemServiceImpl(RequestItemDAO requestItemDAO) {
        this.requestItemDAO = requestItemDAO;
    }

    @Override
    @Transactional
    public void updateRequestItemQuantity(Long id, Long delta) {
        RequestItem requestItem = requestItemDAO.getRequestItem(id);

    }
}
