package com.megacitycab.model;


public class Admin {
  
    private String uname;
    private String pwd;

    public Admin(String uname, String pwd) {
        this.uname = uname;
        this.pwd = pwd;
    }

    public String getUsername() {
        return uname;
    }

    public void setUsername(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String pwd) {
        this.pwd = pwd;
    }
}


