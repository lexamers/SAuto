package Diploma.Service;

import Diploma.Persistence.Entity.Address;
import Diploma.Persistence.Entity.User;

public interface AddressService {
    Long addAddress(Address address);

    Address getLastUserAddress(User user);

    void updateAddress(Address address);
}
