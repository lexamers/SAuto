package Diploma.Service;

import Diploma.Persistence.DAO.AddressDAO;
import Diploma.Persistence.Entity.Address;
import Diploma.Persistence.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressDAO addressDAO;

    @Override
    @Transactional
    public Long addAddress(Address address) {
        return addressDAO.addAddress(address);
    }

    @Override
    @Transactional
    public Address getLastUserAddress(User user) {
        return addressDAO.getLastUserAddress(user);
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }
}
