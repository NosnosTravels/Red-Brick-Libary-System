/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import Constants.SQLConstants;
import Database.ConnectionImpl;
import book.Book;
import book.BookIn;
import book.ISBN;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import repositories.BookService;

/**
 *
 * @author M2200478
 */
public interface BookRepositoryInterface {
    
    List<Book> findByIsbn(ISBN isbn);
    List<Book> findAll();
    void update(Book book);
    void deleteByIsbn(ISBN isbn);
    
    BookIn save(BookIn b) throws SQLException;

}