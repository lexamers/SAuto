package Diploma.Service;

import Diploma.Persistence.DAO.RoleDAO;
import Diploma.Persistence.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public Role getRole(Long id) {
        return roleDAO.getRole(id);
    }
}
