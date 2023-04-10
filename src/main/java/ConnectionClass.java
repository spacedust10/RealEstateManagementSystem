
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import java.sql.*; 
public class ConnectionClass {
    public static Connection getCon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rems","root","2gi20cs181");
            return con;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
