/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Marco
 */
public class UserService {
    
    
    public User getUser(String email) {
        UserDB userDB = new UserDB();
        
            User user = userDB.get(email);
                return user;
    }
    
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    
    public void inactivate(String email) throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(Boolean.FALSE);
        userDB.update(user);
    }
    
    public void reactivate(String email) throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(Boolean.TRUE);
        userDB.update(user);
    }
    
    public void insert(String email, boolean activity, String first_name, String last_name, String password, Role role) throws Exception{
        User user = new User(email, activity, first_name, last_name, password);
        user.setRole(role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, boolean activity, String first_name, String last_name, String password, Role role) throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setPassword(password);
        user.setRole(role);
        userDB.update(user);
    }
    
    
    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
}
