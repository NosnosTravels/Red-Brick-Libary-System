/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import static Constants.SQLConstants.FIND_ALL;
import Exceptions.IllegalArgumentException;
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
import static Constants.SQLConstants.FIND_BY_ISBN;
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

    public BookService(BookRepositoryJdbc jdbc) {}

    public void save(Book book) {
        books.add(book);
        System.out.println("Book Saved: " + book);
    }

    @Override
    public List<Book> findByIsbn(ISBN isbn) {
//        List<Book> books = new ArrayList<>();
        
        try (Connection con = ConnectionImpl.conn();
            PreparedStatement ps = con.prepareStatement(FIND_BY_ISBN)) {
            
            ps.setString(1, isbn.getValue());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                books.add(mapRowToBook(rs));
            }
        }  catch (SQLException ex) {
            throw new RuntimeException("Unable to fetch book", ex);
        }
        return books;        
    }
    
//        List<Book> retVal = new ArrayList<>();
//        for (Book b : books) {
//            if (b.getISBN().equals(isbn)) {
//                retVal.add(b);
//                return retVal;   
//            }
//        }
//        return retVal; 
//    }   
    
    
    
    
    
//        try{Connection con = ConnectionImpl.conn();
//            PreparedStatement ps = con.prepareStatement(FIND_BY_ISBN); 
//            ps.setString(1, isbn.getValue());
//            ResultSet rs = ps.executeQuery();
//            
//            while(rs.next()){
//                books.add(mapRowToBook(rs));
//            }
//        } catch (SQLException ex) {
//            System.getLogger(BookService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
//            throw new RuntimeException("Uable to fetch book", ex);
//        }
//        return books;
 
//
//        System.out.println("Book not found with ISBN: " + isbn);
//        return retVal;
//    }

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
        } catch (SQLException ex) {
            LOGGER.error("Error fetching books", ex);
            throw new RuntimeException("Unable to fetch books", ex);
        }
        LOGGER.info("found books: ", books);
        return books;
    }

    private Book mapRowToBook(ResultSet rs) {
        Book book = new Book();
        try {
            book.setIsbn(new ISBN(rs.getString("ISBN").trim()));
            book.setTitle(rs.getString("Title"));
            book.setAuthor(rs.getString("Author"));
            book.setFormat(BookFormat.valueOf(rs.getString("Format").trim()));
            book.setState(BookState.valueOf(rs.getString("State").trim()));
        } catch (SQLException ex) {
            System.getLogger(BookService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("We got a problem, IllegalArugment" + e.getMessage());
        }
        LOGGER.info(book.toString());
        return book;
    }

    public Book create(ISBN Isbn, String title, String author, String format) throws IllegalArgumentException {
        if (title == null || title.trim().isEmpty()
                || author == null || author.trim().isEmpty()
                || format == null || format.trim().isEmpty()) {
            throw new IllegalArgumentException("missing fields");
        }
        
        Book b = new Book();
        b.setIsbn(Isbn);
        b.setTitle(title);
        b.setAuthor(author);
        b.setFormat(BookFormat.valueOf(format));
        b.setState(BookState.AVAILABLE);
        LOGGER.info("file completed,", books);
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
