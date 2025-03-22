/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.model;

import java.util.Objects;

/**
 *
 * @author OZT00090
 */
public class Vehicle {

    
    
    private final String vehicleType;    
    private final  String vehicleId;    
    private final String lisce;   
    private final String vehicleModel;           
    private final String vehicleBrand;           
    private final int seat;    
    private final String color;
    private final String driverId;
    
    
     private Vehicle(Builder builder) {
        this.vehicleType = builder.vehicleType;
        this.vehicleId = builder.vehicleId;
        this.lisce = builder.lisce;
        this.vehicleModel = builder.vehicleModel;
        this.vehicleBrand = builder.vehicleBrand;
        this.seat = builder.seat;
        this.color = builder.color;
        this.driverId = builder.driverId;
    }

    public String getDriverId() {
        return driverId;
    }



  
    
    public String getVehicleId() {
        return vehicleId;
    }

   

    public String getLisce() {
        return lisce;
    }

 

    public String getVehicleModel() {
        return vehicleModel;
    }

 

    public String getVehicleBrand() {
        return vehicleBrand;
    }

   
    public int getSeat() {
        return seat;
    }

 
    public String getColor() {
        return color;
    }

 
    
  


    public String getVehicleType() {
        return vehicleType;
    }

  

   

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType='" + vehicleType + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", lisce='" + lisce + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", color='" + color + '\'' +
                ", seat=" + seat +
                ", driverId='" + driverId + '\'' +
                 '}';
    } 
    
    
    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return Objects.equals(this.vehicleId, other.vehicleId);
    }
    
    
    

       public static class Builder {
        
        private final String vehicleId;
        // Optional attributes.
        private String vehicleType;
        
        private String vehicleModel;
        private String vehicleBrand;
        private String color;
        private int seat;
     
        private String lisce;
        private String driverId;

        /**
         * Builder constructor with the required vehicleId.
         *
         * @param vehicleId the unique registration number of the vehicle.
         * @throws IllegalArgumentException if vehicleId is null or empty.
         */
        public Builder(String vehicleId,String vehicleType) {
            if (vehicleId == null || vehicleId.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle Id cannot be null or empty.");
            }
            
             if (vehicleType == null || vehicleType.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle Type cannot be null or empty.");
            }
            this.vehicleId = vehicleId;
            this.vehicleType = vehicleType;
        }

     
        
  
        

        public Builder lisce(String lisce) {
            this.lisce = lisce;
            return this;
        }

        public Builder vehicleModel(String vehicleModel) {
            this.vehicleModel = vehicleModel;
            return this;
        }

        public Builder vehicleBrand(String vehicleBrand) {
            this.vehicleBrand = vehicleBrand;
            return this;
        }
        
        
         public Builder color(String color) {
            this.color = color;
            return this;
        }
         
         public Builder seat(int seat) {
            this.seat = seat;
            return this;
        }

     public Builder driverId(String driverId) {
            this.driverId = driverId;
            return this;
        }





        /**
         * Builds and returns a new Customer instance.
         *
         * @return a Customer object constructed with the Builder's parameters.
         */
         
         public Vehicle build(){
         return new Vehicle(this);
                 }

      
        
   
       
    }
}


