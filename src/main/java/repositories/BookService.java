/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import static Constants.SQLConstants.FIND_ALL;
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
import book.BookIn;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author M2200478
 */
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    
    BookRepositoryJdbc bookRepo = new BookRepositoryJdbc();

    public BookIn create(BookIn bookDto) throws IllegalArgumentException, SQLException {
        if (bookDto.getTitle() == null || bookDto.getTitle().trim().isEmpty()
                || bookDto.getIsbn() == null
                || bookDto.getAuthor() == null || bookDto.getAuthor().trim().isEmpty()
                || bookDto.getFormat() == null
                || bookDto.getState() == null) {
            throw new IllegalArgumentException("missing fields");
        }

        BookIn b = new BookIn();
        b.setIsbn(bookDto.getIsbn());
        b.setTitle(bookDto.getTitle());
        b.setAuthor(bookDto.getFormat().toString());
        b.setFormat(BookFormat.valueOf(bookDto.getFormat().toString()));
        b.setState(BookState.AVAILABLE);
        
        BookIn retVal = bookRepo.save(b);
        
        LOGGER.info("file completed,", retVal);
        return retVal;

    }
    
    public List<Book> findByIsbn(ISBN isbn) {
        return bookRepo.findByIsbn(isbn);
    }
    
    public List<Book> findAll() {
        return bookRepo.findAll();
    }
    
    public void deleteByIsbn(ISBN isbn) {
        bookRepo.deleteByIsbn(isbn);
    }

//        for (int i = 0; i < books.size(); i++) {
//            if (books.get(i).getISBN().equals(isbn)) {
//                books.remove(i);
//                System.out.println("Book found for deletion: " + isbn);
//            }
//            System.out.println("Book not found for deletion: " + isbn);
//        }
//    public Book create(ISBN Isbn, String title, String author, String format) throws IllegalArgumentException, SQLException {
//        if (title == null || title.trim().isEmpty()
//                || author == null || author.trim().isEmpty()
//                || format == null || format.trim().isEmpty()) {
//            throw new IllegalArgumentException("missing fields");
//        }
//        
//        BookIn b = new BookIn();
//        b.setIsbn(Isbn);
//        b.setTitle(title);
//        b.setAuthor(author);
//        b.setFormat(BookFormat.valueOf(format));
//        b.setState(BookState.AVAILABLE);
//        LOGGER.info("file completed,", books);
//        return BookRepositoryInterface.save(BookIn b);
//       
//    }
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
