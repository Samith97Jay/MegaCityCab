
package com.megacitycab.service;

import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.model.Vehicle;
import com.megacitycab.dao.VehicleDAO;
import java.sql.SQLException;

/**
 *
 * @author OZT00090
 */
public class VehicleService {
    
   private VehicleDAO vehicledao;

    public VehicleService() {
        vehicledao = new VehicleDAO();
    }

  
    public boolean registerVehicle(String vehicleType, String vehicleId, String lisce,
                                   String vehicleModel, String vehicleBrand, String color, int seat) throws ClassNotFoundException, SQLException {
        int result = vehicledao.insertVehicle(vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat);
        return result > 0;
    }


    public Vehicle getAvailableVehicle(String vehicleType) {
        try {
            return vehicledao.getAvailableVehicle(vehicleType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public Vehicle getAvailableNotBookedVehicle(String vehicleType) {
        try {
            return vehicledao.getAvailableNotBookedVehicle(vehicleType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
