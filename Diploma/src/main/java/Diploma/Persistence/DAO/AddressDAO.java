package Diploma.Persistence.DAO;

import Diploma.Persistence.Entity.Address;
import Diploma.Persistence.Entity.User;

public interface AddressDAO {
    Long addAddress(Address address);

    Address getLastUserAddress(User user);

    void updateAddress(Address address);
}
