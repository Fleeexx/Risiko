/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Max
 */
public class DBConnection {
    
    private Connection conn = null;
    
    public DBConnection(){
    }
    public boolean connect(String name){
        try{
            String url = "jdbc:mysql://localhost:3306/" + name + "?connectTimeout=3000";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.print("Connection to SQLite has been established");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
//        finally {
//            closeConnection();
//        }
        
        return false;
    }
}
