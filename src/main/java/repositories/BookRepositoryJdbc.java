/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import Constants.SQLConstants;
import static Constants.SQLConstants.FIND_ALL;
import static Constants.SQLConstants.FIND_BY_ISBN;
import Database.ConnectionImpl;
import book.Book;
import book.BookFormat;
import book.BookIn;
import book.BookState;
import book.ISBN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author M2200478
 */
public class BookRepositoryJdbc implements BookRepositoryInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryJdbc.class);

    private final List<Book> books = new ArrayList<>();
    
        public void save(Book book) {
        books.add(book);
        System.out.println("Book Saved: " + book);
    }

    @Override
    public BookIn save(BookIn b) throws SQLException {

        String sql = SQLConstants.INSERT;

        try (
                var conn = ConnectionImpl.conn(); var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, b.getTitle());
            pstmt.setString(2, b.getAuthor());
            pstmt.setString(3, b.getIsbn().toString());
            pstmt.setString(4, b.getState().toString());
            pstmt.setString(5, b.getFormat().toString());
            pstmt.executeUpdate();

            return b;
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Book> findByIsbn(ISBN isbn) {
//        List<Book> books = new ArrayList<>();

        try (Connection con = ConnectionImpl.conn(); PreparedStatement ps = con.prepareStatement(FIND_BY_ISBN)) {

            ps.setString(1, isbn.getValue());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Unable to fetch book", ex);
        }
        return books;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection con = ConnectionImpl.conn(); PreparedStatement ps = con.prepareStatement(FIND_ALL); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                books.add(mapRowToBook(resultSet));
            }
        } catch (SQLException ex) {
            LOGGER.error("Error fetching books", ex);
            throw new RuntimeException("Unable to fetch books", ex);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("We got a problem, IllegalArugment" + e.getMessage());
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
        if (isbn == null || isbn.getValue() == null) {
            throw new IllegalArgumentException("ISBN cannot be null");
        }
        try (Connection con = ConnectionImpl.conn(); PreparedStatement ps = con.prepareStatement(SQLConstants.DELETE)) {

            ps.setString(1, isbn.getValue().replaceAll("\\s", ""));
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book deleted: " + isbn.getValue());
            } else {
                System.out.println("No book found with ISBN: " + isbn.getValue());
            }
        } catch (SQLException ex) {
            System.getLogger(BookService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
