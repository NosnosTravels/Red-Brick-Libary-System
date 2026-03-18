/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author M2200478
 */
public class Libary {

    String libaryName;
    String location;
    int libaryID;
    private static final Logger LOGGER = LoggerFactory.getLogger(Libary.class);

    public Libary() {
    }

    public void Libary() {
        this.libaryName = libaryName;
        this.location = location;
        this.libaryID = libaryID;

    }

    public String getLibaryName() {
        return libaryName;
    }

    public void setLibaryName(String libaryName) {
        this.libaryName = libaryName;
    }

    public String getLocation() {
        return location;
    }

   
    public void setLocation(String location) {
        this.location = location;
    }

    public int getLibaryID() {
        return libaryID;
    }

    public void setLibaryID(int libaryID) {
        this.libaryID = libaryID;
    }

//    LOGGER.info ("No Libary found for {}", currentLoggedInUser());
//    LOGGER.debug ("");
//    LOGGER.error ("");
//    LOGGER.warn ("");
}
