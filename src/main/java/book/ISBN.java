/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book;

import Exceptions.IllegalArgumentException;
import java.util.Objects;        
        

/**
 *
 * @author M2200478
 */
public class ISBN {
    private final String value;
    
    public ISBN() {
        this.value = null;
    }
    
    public ISBN (String value) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be blank");
        }
        
        String cleaned = value.trim();
        
        if (!cleaned.matches("[0-9\\-Xx+]")) {
         throw new IllegalArgumentException("Invalid ISBN format: ");   
        }
        this.value = cleaned;
    }
    public String getValue() {
        return value;
    }
    
    public String getDigitsOnly() {
        return value == null ? null : value.replace("-", " ");
    }
    
    @Override
    public String toString() {
        return value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ISBN other)) return false;
        return Objects.equals(getDigitsOnly(), other.getDigitsOnly());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getDigitsOnly());
    }
    
}
//    private String value;
//    
//    public ISBN(String value) {
//        this.value = value;
//    }
//    
//    public String getValue(){
//        return this.value;
//    }
//    
//    public boolean equals (Object obj){
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        ISBN isbn = (ISBN) obj;
//        return value.equals(isbn.value);
//    }
//    public int hashCode(){
//        return value.hashCode();
//    }
//}
