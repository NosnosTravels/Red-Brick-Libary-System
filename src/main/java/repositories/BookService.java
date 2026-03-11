/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import static Constants.SQLConstants.FIND_ALL;
import Exceptions.IllegalArugmentException;
import book.Book;
import book.BookFormat;
import book.BookState;
import book.ISBN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Constants.SQLConstants;
import Database.ConnectionImpl;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author M2200478
 */
public class BookService implements BookRepositoryInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    
    private final List<Book> books = new ArrayList<>();

    public void save(Book book) {
        books.add(book);
        System.out.println("Book Saved: " + book);
    }

    @Override
    public List<Book> findByIsbn(ISBN isbn) {
        List<Book> retVal = new ArrayList<>();
        for (Book b : books) {
            if (b.getISBN().equals(isbn)) {
                retVal.add(b);
            }
        }

        System.out.println("Book not found with ISBN: " + isbn);
        return retVal;
    }
    
        @Override
    public void update(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book existingBook = books.get(i);
            if (existingBook.getISBN().equals(updatedBook.getISBN())) {
                books.set(i, updatedBook);
                System.out.println("Book updated: " + updatedBook);
                return;
            }
        }
        System.out.println("Book not found for update: " + updatedBook.getISBN());
    }

    @Override
    public void deleteByIsbn(ISBN isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(isbn)) {
                books.remove(i);
                System.out.println("Book not found for deletion: " + isbn);
            }
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection con = ConnectionImpl.conn(); 
            PreparedStatement ps = con.prepareStatement(FIND_ALL); 
            ResultSet resultSet = ps.executeQuery()) {
            
                while (resultSet.next()) {
                    books.add(mapRowToBook(resultSet));
                }
        }catch (SQLException ex) {
            LOGGER.error("Error");
            throw new RuntimeException("Unable to fetch books");
        } catch (IllegalArugmentException ex) {
            System.getLogger(BookService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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

    public Book create(ISBN Isbn, String title, String author, String format) {
        if (title == null || title.trim().isEmpty()
                || author == null || author.trim().isEmpty()
                || format == null || format.trim().isEmpty()){
            throw new IllegalArgumentException ("missing fields");
        }
        Book b = new Book();
        b.setIsbn(Isbn);
        b.setTitle(title);
        b.setAuthor(author);
        b.setFormat(BookFormat.valueOf(format));
        b.setState(BookState.AVAILABLE);
        return BookRepositoryInterface.save(b);
    }


}
        

//class bookServices 
//
//    (){
//    find all
//
//    private Book mapRowToBook(ResultSet rs) throws SQLException, IllegalArugmentException {
//        Book book = new Book();
//        book.setIsbn(new ISBN(rs.getString("ISBN")));
//        book.setTitle(rs.getString("title"));
//        book.setAuthor(rs.getString("author"));
//        book.setFormat(BookFormat.valueOf("FORMAT"));
//        book.setState(BookState.valueOf(rs.getString("STATE")));
//        return book;
//    }
//
//    find by isbn book create
//}
//}
