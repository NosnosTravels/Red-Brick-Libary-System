/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

import book.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Database.ConnectionImpl;
import book.BookFormat;
import book.BookState;
import book.ISBN;


/**
 *
 * @author M2200478
 */
public class DataAccessException extends RuntimeException{

    public DataAccessException(String database_timeout, SQLException ex) {
    }
 private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessException.class);

    private static final String FIND_ALL = "SELECT ISBN, Title, Author, Format, State FROM Books";

    public List<Book> findAll() throws IllegalArugmentException {
        List<Book> books = new ArrayList<>();

        try (Connection con = ConnectionImpl.conn();
             PreparedStatement ps = con.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }

        } catch (SQLTimeoutException ex) {
            LOGGER.error("Database timeout while fetching books", ex);
        } catch (SQLSyntaxErrorException ex) {
            LOGGER.error("SQL syntax error in FIND_ALL query", ex);
        } catch (SQLException ex) {
            LOGGER.error("General SQL error while fetching books", ex);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Bad data in books table (enum mapping failed)", ex);
        } catch (RuntimeException ex) {
            LOGGER.error("Unexpected error while fetching books", ex);
            throw ex;
        }

        return books;
    }
    private  Book mapRowToBook(ResultSet rs) throws SQLException, IllegalArugmentException {
        Book book = new Book();
        book.setIsbn(new ISBN(rs.getString("ISBN")));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setFormat(BookFormat.valueOf("FORMAT"));
        book.setState(BookState.valueOf(rs.getString("STATE")));
        return book;
    }
}
    
//private Book mapRowToBook(ResultSet rs) throws SQLException, IllegalArugmentException {
//    Book book = new Book(title, author, isbn);
//    String title = rs.getString("Title"");
//    String author = rs.getString("Author");
//    String isbnStr = rs.getString("ISBN");
//
//    ISBN isbn = new ISBN(isbnStr); // assumes ISBN has a constructor taking a string
//
//    return new Book(title, author, isbn);
    
    


//    public DataAccessException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public DataAccessException(String message) {
//        super(message);
//    }
//}
    
    
