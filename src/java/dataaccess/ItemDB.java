/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author Marco
 */
public class ItemDB {
    
    public Item get(int itemId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Item item = em.find(Item.class, itemId);
            return item;
        } finally {
            em.close();
        }
    }
    
    public int deleteItem(Item item) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        int numberOfRows = 0;
        
        try{
            trans.begin();
            em.remove(em.merge(item));
            trans.commit();
            numberOfRows = 1;
        } catch (Exception e){
            trans.rollback();
            Logger.getLogger(ItemDB.class.getName()).log(Level.SEVERE, "Cannot delete " + item.toString(), e);
        } finally{
            em.close();
        }
        return numberOfRows;
    }
    
    public void deleteItem(List<Item> listItem) throws Exception {
        for (Item items: listItem) {
            deleteItem(items);
        }
    }
        
    public List<Item> getAll() throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
        
    }
    
    
    public List<Item> getByOwner(User user) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        //return em.createNamedQuery("Item.findAll", Item.class).getResultList();
        TypedQuery<Item> query = em.createNamedQuery("Item.findByOwner", Item.class);
        return query.setParameter("owner", user).getResultList();
    }
    
    public List<Item> getByOwner2(String email) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        //return em.createNamedQuery("Item.findAll", Item.class).getResultList();
        TypedQuery<Item> query = em.createNamedQuery("Item.findByOwner2", Item.class);
        return query.setParameter("owner", email).getResultList();
    }
        /*EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        return query.setParameter("email", email).getSingleResult();
          */  
    
    public void insert(Item item) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        try{
            User user = item.getOwner();
            user.getItemList().add(item);
            
            Category category = item.getCategory();
            category.getItemList().add(item);
            
            trans.begin();
            em.persist(item);
            em.merge(user);
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
        
    
    public void update(Item item) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(item);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
}
