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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class InsertData {
    
    public static void main(String[] args) {
        String url = "jdbc:sqlite:risiko.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            System.out.println("Frage eingeben:");
            Scanner sc = new Scanner(System.in);
            String frage = sc.nextLine();
            System.out.print("Kategorie (");
            for (int i = 0; i < 4; i++) {
                System.out.print(Gui.KATEGORIEN[i]);
                if (i < 3) {
                    System.out.print(" | ");
                }
            }
            System.out.println("):");
            String kategorie = sc.nextLine();
            System.out.println("Punkte eingeben (100 | 200 | 300 | 400 | 500 | 1000):");
            int points = Integer.parseInt(sc.nextLine());
            String[] antwort = new String[4];
            System.out.println("(1) Richtige Antwort:");
            antwort[0] = sc.nextLine();
            System.out.println("(2) Falsche Antwort:");
            antwort[1] = sc.nextLine();
            System.out.println("(3) Falsche Antwort:");
            antwort[2] = sc.nextLine();
            System.out.println("(4) Falsche Antwort:");
            antwort[3] = sc.nextLine();
            Statement st = null;
            try {
                st = conn.createStatement();
                st.executeUpdate("INSERT INTO frage (frage, points, kategorie) VALUES ('" + frage + "', " + points + ", '" + kategorie + "');");
                st.close();
                ResultSet rs = null;
                rs = conn.createStatement().executeQuery("SELECT f_id as id FROM frage ORDER BY f_id DESC LIMIT 1;");
                rs.next();
                for (int i = 0; i < 4; i++) {
                    st = conn.createStatement();
                    int b = ((i == 0) ? 1 : 0);
                    int id = rs.getInt("id");
                    System.out.println(b);
                    System.out.println(antwort[i]);
                    System.out.println("ID: " + id);
                    st.executeUpdate("INSERT INTO antwort (f_id, antwort, iscorrect) VALUES (" + id + ", '" + antwort[i] + "', " + b + ");");
//                    st.executeUpdate("INSERT INTO antwort (f_id, antwort, iscorrect) VALUES (" + rs.getInt(0) + ", '" + antwort[i] + "', " + b + ");");
                    st.close();
                }
                rs.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
