package services;

import dataaccess.DBUtil;
import models.Role;
import dataaccess.RoleDB;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Marco
 */
public class RoleService {
    
    public Role get(int roleID) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        return role;
    }
    
    public List<Role> getAll() throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        return em.createNamedQuery("Role.findAll", Role.class).getResultList();
 
    }
}
