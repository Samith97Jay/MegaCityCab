/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.service;

import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Driver;
import java.sql.SQLException;

/**
 *
 * @author OZT00090
 */
public class DriverService {
    
    
    private DriverDAO driverDAO;

    public DriverService() {
        driverDAO = new DriverDAO();
    }

    /**
     * Registers a new driver.
     *
     * @param driver the driver object to register.
     * @return true if registration was successful; false otherwise.
     */
    public boolean registerDriver(Driver driver) throws SQLException, ClassNotFoundException {
        // Additional business logic can be added here (e.g., validations)
        return driverDAO.insertDriver(driver);
    }
    
    
    public Driver getDriverById(String driverId) throws Exception {
        return driverDAO.getDriver(driverId);
    }
    
  
    public boolean updateDriver(Driver driver) throws Exception {
        return driverDAO.updateDriver(driver);
    }
    
    public boolean deleteDriver(String driverId) throws SQLException, ClassNotFoundException {
        return driverDAO.deleteDriver(driverId);
    }
    
}
