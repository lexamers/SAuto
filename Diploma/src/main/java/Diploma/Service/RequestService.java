package Diploma.Service;

import Diploma.Persistence.Entity.Request;
import java.util.List;

public interface RequestService {
    Request getRequest(Long id);
    Long addRequest(Request request);
    void updateRequest (Request request);
    List<Request> requestsInProcessing();
}
