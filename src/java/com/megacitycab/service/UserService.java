/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.service;

import java.util.List;
import com.megacitycab.dao.UserDAO;
import com.megacitycab.model.User;

/**
 *
 * @author OZT00090
 */
public class UserService {
    
   
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }
    
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
    
}
