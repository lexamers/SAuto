package Diploma.Service;

import Diploma.Persistence.Entity.Request;
import Diploma.Persistence.Entity.RequestItem;

public interface RequestItemService {
    void addRequestItem(RequestItem requestItem);

    RequestItem getByRequest(Request request);

    void removeRequestItem(Long id);

    void updateRequestItemQuantity(Long id, Long delta);

}
