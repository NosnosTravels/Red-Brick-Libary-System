/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReaderWriter;

import java.io.BufferedWriter;
import Constants.SQLConstants;
import static Constants.SQLConstants.INSERT;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author M2200478
 */
public class ToDatabase {

       public static void FileToDatabase() throws IOException, SQLException{
        final String FILE_NAME = "books.txt";
        String Line;
        String url = "jdbc:sqlite:src/database/database.db";
        try (
                BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
                Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(INSERT)) {
            while ((Line = br.readLine()) != null) {
                String[] data = Line.split("\\|");

                pstmt.setString(1, data[0]);
                pstmt.setString(2, data[1]);
                pstmt.setString(3, data[2]);
                pstmt.setString(4, data[3]);
                pstmt.setString(5, data[4]);
                
                pstmt.executeUpdate();
            }
            System.out.println("Data has been inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
