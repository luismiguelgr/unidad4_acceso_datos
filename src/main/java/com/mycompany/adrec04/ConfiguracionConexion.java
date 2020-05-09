/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec04;

/**
 *
 * @author miguel
 */
public class ConfiguracionConexion {
    private String address;
    private String port;
    private String name;
    private String user;
    private String password;

    public ConfiguracionConexion(){
        
    }
    
    public ConfiguracionConexion(String address, String port, String name, String user, String password){
        this.address = address;
        this.name = name;
        this.port = port;
        this.user = user;
        this.password = password;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
