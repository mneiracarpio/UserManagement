/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;



/**
 *
 * @author Marco
 */
public class ItemService {
    
    public List<Item> getAll() throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll();
        return items;
    }
    
    public List<Item> getByOwner(User user) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getByOwner(user);
        return items;
    }
    
    
    public List<Item> getByOwner2(String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getByOwner2(email);
        return items;
    }
    
    public void insert(String itemName, double price, Category category, String email) throws Exception{
        //Item item = new Item( 0 , itemName, price);
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setCategory(category);
        
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        item.setOwner(user);
        
        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
    }
    
    
    public void delete(int itemId) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemId);
        itemDB.deleteItem(item);
    }
    
    
    public void update(int itemId, String itemName, double price, Category category) throws Exception{
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemId);

        item.setItemName(itemName);
        item.setPrice(price);
        item.setCategory(category);
        itemDB.update(item);
        
    }
    
    
    public Item getItem(int itemId) {
        ItemDB itemDB = new ItemDB();
        
            Item item = itemDB.get(itemId);
                return item;
    }
    
}
