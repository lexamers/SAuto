package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Request;
import Diploma.Persistence.Entity.User;

import java.util.List;

public interface RequestDAO {
    Request getRequest(Long id);

    Long addRequest(Request request);

    void updateRequest(Request request);

    Request getLastUserRequest(User user);

    List<Request> requestsInProcessing();
}
