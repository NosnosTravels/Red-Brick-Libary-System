/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constants;

import java.sql.PreparedStatement;


/**
 *
 * @author M2200478
 */
public class SQLConstants {
    public static final String URL = "jdbc:sqlite:src/database/database.db";
    
    public static final String INSERT = "INSERT INTO Book(title, author, isbn,  format, state) VALUES (?,?,?,?,?);";
    public static final String FIND_BY_ISBN = "SELECT ISBN, Title, Author, Format, State FROM Book WHERE isbn = ?;";
    public static final String FIND_ALL = "SELECT Title, Author, ISBN, State, Format  FROM Book;";
    public static final String UPDATE = "UPDATE Book SET Title =?, SET Author =?, SET Format =?, SET State =? WHERE ISBN =?;";
    public static final String DELETE = "DELETE FROM Book WHERE ISBN =?;";
//    The question marks above are placeholders to be set later
    
//    Examople how to use
//      PreparedStatement pstmt = connection.preparedStatement(SQLConstants,INSERT);
   
}
