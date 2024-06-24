package com.example.basedatos.model;

public class user {
    private String name;
    private String password;
    private String repitpassword;

    public user(String name, String password, String repitpassword) {
        this.name = name;
        this.password = password;
        this.repitpassword = repitpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepitpassword() {
        return repitpassword;
    }

    public void setRepitpassword(String repitpassword) {
        this.repitpassword = repitpassword;
    }

}
