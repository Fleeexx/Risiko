/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Max
 */
public class DBConnection {
    
    private Connection conn = null;
    
    public DBConnection(){
    }
    public void startConnection(String name){
        try{
            String url = "jdbc:mysql://localhost:3306/" + name + "?connectTimeout=3000";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.print("Connection to SQLite has been established");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }   
    public void closeConnection(){
        try{
            if(conn != null){
                conn.close();
            }
        }
        catch(SQLException e){
            System.out.print(e.getMessage());
        }
    }
    public ResultSet executeSQLQuery(String sql){
        ResultSet rs = null;
        try{
            rs = conn.createStatement().executeQuery(sql);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }
     public void insert(String sql){
         Statement st = null;
         try{
             st = conn.createStatement();
             st.executeUpdate(sql);
             st.close();
         }
            catch(Exception e){
         }
    } 
}

