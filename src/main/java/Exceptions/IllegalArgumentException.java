/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author M2200478
 */
public class IllegalArgumentException extends Exception {
    public IllegalArgumentException(String message) {
        super(message); 
    }
    
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause); 
    }
}
