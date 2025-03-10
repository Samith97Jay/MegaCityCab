/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.model;

/**
 *
 * @author OZT00090
 */
public class User {
    
        
   
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String address;
    private String mobileno;
    private String nic;
    private String gender;

    public User(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(String fullname, String username, String email, String password, String address, String mobileno, String nic, String gender) {
       
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobileno = mobileno;
        this.nic = nic;
        this.gender = gender;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{fullname=" + fullname + ", username=" + username + ", email=" + email + ", password=" + password + ", address=" + address + ", mobileno=" + mobileno + ", nic=" + nic + ", gender=" + gender + '}';
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
 
   
}
