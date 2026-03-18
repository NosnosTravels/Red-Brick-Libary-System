/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLiteQueries;

import static Constants.SQLConstants.URL;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author M2200478
 */
public class DropTables {

    public static void dropDatabase() {
        // SQL statement for dropping tables
        var DropBooks = "	DROP TABLE Book;";
        var DropMembers = "	DROP TABLE Members;";
        var DropStaff = "	DROP TABLE Staff;";
        var DropLibary = "	DROP TABLE Libary;";
        var DropBB = "	DROP TABLE Borrowed_Books;";
//connection to db in the try catch
        try (var conn = DriverManager.getConnection(URL); var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DropBooks);
            stmt.execute(DropMembers);
            stmt.execute(DropStaff);
            stmt.execute(DropLibary);
            stmt.execute(DropBB);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DropBookTable() {
        // SQL statement for dropping tables
        var DropBooks = "	DROP TABLE Book;";

//connection to db in the try catch
        try (var conn = DriverManager.getConnection(URL); var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DropBooks);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DropMembersTable() {
        // SQL statement for dropping tables
        var DropMembers = "	DROP TABLE Members;";
//connection to db in the try catch
        try (var conn = DriverManager.getConnection(URL); var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DropMembers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DropStaffTable() {
        // SQL statement for dropping tables

        var DropStaff = "	DROP TABLE Staff;";
        var DropLibary = "	DROP TABLE Libary;";
        var DropBB = "	DROP TABLE Borrowed_Books;";
//connection to db in the try catch
        try (var conn = DriverManager.getConnection(URL); var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DropStaff);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DropLibaryTable() {
        // SQL statement for dropping tables

        var DropLibary = "	DROP TABLE Libary;";
        var DropBB = "	DROP TABLE Borrowed_Books;";
//connection to db in the try catch
        try (var conn = DriverManager.getConnection(URL); var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DropLibary);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DropBBTable() {
        // SQL statement for dropping tables
        var DropBB = "	DROP TABLE Borrowed_Books;";
        //connection to db in the try catch
        try (var conn = DriverManager.getConnection(URL); var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DropBB);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DropUsersTable() {
        // SQL statement for dropping tables
        var DropUsers = "	DROP TABLE Users;";
        //connection to db in the try catch
        try (var conn = DriverManager.getConnection(URL); var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DropUsers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
