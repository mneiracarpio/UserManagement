package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Role;

/**
 *
 * @author Marco
 */
public class CategoryDB {
    
    
    public Category get(Integer categoryId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Category category = em.find(Category.class, categoryId);
            return category;
        } finally {
            em.close();
        }
    }
    
    /**public List<Category> getAll() throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        return em.createNamedQuery("Caregory.findAll", Category.class).getResultList();
        
    }**/
    
    
    public void insert(Category category) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        try{
            //User user = item.getOwner();
            //user.getItemList().add(item);
            
            //Category category = item.getCategory();
            //category.getItemList().add(item);
            
            trans.begin();
            em.persist(category);
            //em.merge(user);
            //em.merge(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
        
    public void update(Category category) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
}
