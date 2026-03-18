/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;

import static Constants.SQLConstants.INSERT;
import static Constants.SQLConstants.URL;
import SQLiteQueries.CreateTables;
import SQLiteQueries.DropTables;


import book.BookFormat;
import http.HttpService;
import java.io.IOException;
import book.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import staff.Staff;
import staff.Address;
import libary.Libary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ReaderWriter.Buffered_Reader;
import ReaderWriter.ToDatabase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import http.JSHandler;


        
        
/**
 *
 * @author M2200478
 */
public class LibaryChalleges {
    private static final Logger LOGGER = LoggerFactory.getLogger(LibaryChalleges.class);
    static Connection conn;

    public static void main(String[] args) throws SQLException, IOException{

        HttpService.startServer(8081);
//        HttpService.stopServer();

//      Reads the book.txt file
//          Buffered_Reader.bufferedReader();
//      Writes the txt file to the database
//            ToDatabase.FileToDatabase();
        
        
//    Conencts to the database
//        connect();

//    Creates the tables
    //    CreateTables.EnableForeignKey();
//        CreateTables.BookTable();
    //    CreateTables.MemberTable();
    //    CreateTables.StaffTable();
    //    CreateTables.LibaryTable();
    //    CreateTables.UsersTable();
//        CreateTables.BorrowedBooksTable();
    
//    Creates all of the tables
        CreateTables.CreateAllTables();
      
//    Deletes the database
//      DropTables.dropDatabase();

//    Drops each table
//        DropTables.DropBookTable();
//        DropTables.DropMembersTable();
//        DropTables.DropStaffTable();
//        DropTables.DropLibaryTable();
//        DropTables.DropUsersTable();
//        DropTables.DropBBTable();


        

    }
        
    
    public static void connect() throws SQLException {
        System.out.println("""
                           
                           ======================
                           Connecting to databse
                           ======================""");
        
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("""
                               
                               ======================
                               Connection Successfull
                               ======================""");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if( conn != null) {
                conn.close();
            }
            System.out.println("""

               =======================
               Connection Disconnected
               =======================""");
        }
    }
}

////  Staff Object
//        Staff staffObj = new Staff();
//        staffObj.setFirstname("Harrison");
//        staffObj.setLastname("Smith");
//        Address address = new Address();
//        address.setAddress1("add1");
//        address.setAddress2("address2");
//        address.setTown("Middlesbrough");
//        staffObj.setStaffAddress(address);
//
////  Displays the book      
//        BookRecord newBook = new BookRecord("Name", "stirng", 1233525, BookFormat.EBOOK);
//        Book book = new Book("title", "author", 13213123);
//        System.out.println(book);
//
//        //Libary Object
//        Libary libaryObj = new Libary();
//        libaryObj.setLibaryName("Middlesbrough Libary");
//        libaryObj.setLocation("Middlesbrough");
//        libaryObj.setLibaryID(22445687);
////      System.out.println(libaryObj);
//    }

