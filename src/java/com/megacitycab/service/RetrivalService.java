package com.megacitycab.service;

import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Vehicle;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;

public class RetrivalService {

    private CustomerDAO customerDAO;
    private DriverDAO driverDAO;
    private VehicleDAO vehicleDAO;
    private CustomerService customerService;

    public RetrivalService() {
     
        driverDAO = new DriverDAO();
        customerDAO = CustomerDAO.getInstance();
        vehicleDAO = new VehicleDAO();
        customerService = new CustomerService();
    }
    



    public Customer getCustomer(String custId) {
        try {
            return customerDAO.getCustomer(custId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Customer getCustomerByNic(String nic) {
        try {
            return customerService.fetchCustomerByNic(nic);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Driver getDriver(String driverId) {
        try {
            return driverDAO.getDriver(driverId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vehicle getVehicle(String vehicleId) {
        try {
            return vehicleDAO.getVehicle(vehicleId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateCustomer(Customer customer) {
        try {
            return customerDAO.updateCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDriver(Driver driver) {
        try {
            return driverDAO.updateDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVehicle(Vehicle vehicle) {
        try {
            return vehicleDAO.updateVehicle(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
     public boolean deleteCustomer(String custId) {
        try {
            return customerDAO.deleteCustomer(custId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     
         public boolean deleteDriver(String driverId) {
        try {
            return driverDAO.deleteDriver(driverId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
         
           public boolean deleteVehicle(String vehicleId) {
        try {
            return vehicleDAO.deleteVehicle(vehicleId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



 

}
