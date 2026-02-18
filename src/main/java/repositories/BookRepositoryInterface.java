/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import book.Book;
import book.ISBN;
import java.util.List;

/**
 *
 * @author M2200478
 */
public interface BookRepositoryInterface {
    
    List<Book> findByIsbn(ISBN isbn);
    List<Book> findAll();
    void update(Book book);
    void deleteByIsbn(ISBN isbn);
    
    static Book save(Book b){
        
        return b;
        
    }
}
