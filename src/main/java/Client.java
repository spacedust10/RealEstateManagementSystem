
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class Client {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    
    public int getId(){
        return this.id;
    }
    public void setId(int ID){
        this.id = ID; 
    }
    
    public String getname(){
        return this.name;
    }
    public void setname(String NAME){
        this.name = NAME;
    }
    
    public String getphone(){
        return this.phone;
    }
    public void setphone(String PHONE){
        this.phone = PHONE;
    }
    
    public String getemail(){
        return this.email;
    }
    public void setemail(String EMAIL){
        this.email = EMAIL;
    }
    
    public String getaddress(){
        return this.address;
    }
    public void setaddress(String ADDRESS){
        this.address = ADDRESS;
    }
    
    public Client(){}
    public Client(int id, String name, String phone, String email, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    public boolean addNEWclient(Client client){
        PreparedStatement ps;
        String addquery = "insert into clients (name,phone,email,address) values(?,?,?,?)";
        try {
            ps = ConnectionClass.getCon().prepareStatement(addquery);
            ps.setString(1, client.getname());
            ps.setString(2, client.getphone());
            ps.setString(3, client.getemail());
            ps.setString(4, client.getaddress());
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean editClient(Client client){
        PreparedStatement ps;
        String editquery = "update clients set name = ?, phone = ?, email = ?, address = ? where clientId = ?";
        try {
            ps = ConnectionClass.getCon().prepareStatement(editquery);
            ps.setString(1, client.getname());
            ps.setString(2, client.getphone());
            ps.setString(3, client.getemail());
            ps.setString(4, client.getaddress());
            ps.setInt(5, client.getId());
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteClient(int id){
        PreparedStatement ps;
        String deletequery = "delete from clients where clientId = ?";
        try {
            ps = ConnectionClass.getCon().prepareStatement(deletequery);
            ps.setInt(1, id);
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Owner.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<Client> clientList(){
        ArrayList<Client> list = new ArrayList<>();
        Statement st;
        ResultSet rs;
        String selectquery = "select * from clients";
        try {
            st = ConnectionClass.getCon().createStatement();
            rs = st.executeQuery(selectquery);
            Client client;
            while(rs.next()){
                client = new Client(rs.getInt(1),
                                  rs.getString(2),
                                  rs.getString(3),
                                  rs.getString(4),
                                  rs.getString(5));
                list.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
}
