/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
public class Owner {
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
    
    public Owner(){}
    public Owner(int id, String name, String phone, String email, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    public boolean addNEWowner(Owner owner){
        PreparedStatement ps;
        String addquery = "insert into owners (name,phone,email,address) values(?,?,?,?)";
        try {
            ps = ConnectionClass.getCon().prepareStatement(addquery);
            ps.setString(1, owner.getname());
            ps.setString(2, owner.getphone());
            ps.setString(3, owner.getemail());
            ps.setString(4, owner.getaddress());
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Owner.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean editOwner(Owner owner){
        PreparedStatement ps;
        String editquery = "update owners set name = ?, phone = ?, email = ?, address = ? where ownerId = ?";
        try {
            ps = ConnectionClass.getCon().prepareStatement(editquery);
            ps.setString(1, owner.getname());
            ps.setString(2, owner.getphone());
            ps.setString(3, owner.getemail());
            ps.setString(4, owner.getaddress());
            ps.setInt(5, owner.getId());
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Owner.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteOwner(int id){
        PreparedStatement ps;
        String deletequery = "delete from owners where ownerId = ?";
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
    
    public ArrayList<Owner> ownersList(){
        ArrayList<Owner> list = new ArrayList<>();
        Statement st;
        ResultSet rs;
        String selectquery = "select * from owners";
        try {
            st = ConnectionClass.getCon().createStatement();
            rs = st.executeQuery(selectquery);
            Owner owner;
            while(rs.next()){
                owner = new Owner(rs.getInt(1),
                                  rs.getString(2),
                                  rs.getString(3),
                                  rs.getString(4),
                                  rs.getString(5));
                list.add(owner);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Owner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
