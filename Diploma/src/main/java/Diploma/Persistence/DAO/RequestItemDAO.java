package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Request;
import Diploma.Persistence.Entity.RequestItem;

public interface RequestItemDAO {
    void addRequestItem(RequestItem requestItem);

    RequestItem getByRequest(Request request);

    void removeRequestItem(Long id);

    RequestItem getRequestItem(Long id);
}
