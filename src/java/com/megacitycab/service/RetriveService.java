package com.megacitycab.service;

import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Vehicle;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;

public class RetriveService {

    private CustomerDAO customerDAO;
    private DriverDAO driverDAO;
    private VehicleDAO vehicleDAO;
    private CustomerService customerService;

    public RetriveService() {
        customerDAO = CustomerDAO.getInstance();
        driverDAO = new DriverDAO();
        vehicleDAO = new VehicleDAO();
        customerService = new CustomerService();
    }

    public Customer getCustomer(String registrationNumber) {
        try {
            return customerDAO.getCustomer(registrationNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Customer getCustomerByNic(String nic) {
        try {
            return customerService.getCustomerByNic(nic);
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
}
