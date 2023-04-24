package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;
import dataaccess.DBUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Marco
 */
public class CategoryService {
    
    public Category get(int categoryID) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryID);
        return category;
    }
        
    public List<Category> getAll() throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        return em.createNamedQuery("Category.findAll", Category.class).getResultList();
    }

    public void insert(String categoryName) throws Exception{
        //Item item = new Item( 0 , itemName, price);
        Category category = new Category();
        category.setCategoryName(categoryName);
        
        //UserDB userDB = new UserDB();
        //User user = userDB.get(email);
        //item.setOwner(user);
        
        CategoryDB categoryDB = new CategoryDB();
        categoryDB.insert(category);
    }
    
    
    public void update(int categoryId, String categoryName) throws Exception{
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryId);
        category.setCategoryName(categoryName);
        categoryDB.update(category);        
    }
    
}


