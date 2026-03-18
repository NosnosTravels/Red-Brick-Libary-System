/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author M2200478
 */
public class IllegalArugmentException extends Exception {
    public IllegalArugmentException(String message) {
        super(message); 
    }
    
    public IllegalArugmentException(String message, Throwable cause) {
        super(message, cause); 
    }
}
