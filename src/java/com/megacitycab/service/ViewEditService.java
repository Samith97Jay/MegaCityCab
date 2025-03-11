package com.megacitycab.service;

import com.megacitycab.dao.CarRegistrationDAO;
import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Car;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;

public class ViewEditService {

    private CustomerDAO customerDAO;
    private DriverDAO driverDAO;
    private CarRegistrationDAO carDAO;
    private CustomerService customerService;

    public ViewEditService() {
        customerDAO = CustomerDAO.getInstance();
        driverDAO = new DriverDAO();
        carDAO = new CarRegistrationDAO();
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

    public Car getCar(String vehicleRegId) {
        try {
            return carDAO.getCar(vehicleRegId);
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

    public boolean updateCar(Car car) {
        try {
            return carDAO.updateVehicle(car);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
