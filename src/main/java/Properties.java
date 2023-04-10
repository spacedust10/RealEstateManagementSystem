/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Properties {
    private int propId;
    private String type;
    private String price;
    private String ownerId;
    private String description;
    private String address;
    
    public int getpropId(){
        return this.propId;
    }
    public void setpropId(int ID){
        this.propId = ID;
    }
    
    public String gettype(){
        return this.type;
    }
    public void settype(String TYPE){
        this.type = TYPE;
    }
    
    public String getprice(){
        return this.price;
    }
    public void setprice(String PRICE){
        this.price = PRICE;
    }
    
    public String getownerid(){
        return this.ownerId;
    }
    public void setownerid(String OWNERid){
        this.ownerId = OWNERid;
    }
    
    public String getdescription(){
        return this.description;
    }
    public void setdescription(String DESCRIPTION){
        this.description = DESCRIPTION;
    }
    
    public String getaddress(){
        return this.address;
    }
    public void setaddress(String ADDRESS){
        this.address = ADDRESS;
    }
    
    public Properties(){};
    public Properties(int id, String type, String price,String ownerid,String desc, String address){
        this.propId = id;
        this.type = type;
        this.price = price;
        this.ownerId = ownerid;
        this.description = desc;
        this.address = address;
    }
    
    public boolean addproperty(Properties property){
        PreparedStatement ps;
        String addquery = "insert into properties(propertyType,basePrice,ownerId,description,address) values(?,?,?,?,?)";
        try {
            ps = ConnectionClass.getCon().prepareStatement(addquery);
            ps.setString(1, property.gettype());
            ps.setString(2, property.getprice());
            ps.setString(3, property.getownerid());
            ps.setString(4, property.getdescription());
            ps.setString(5, property.getaddress());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }       
    }
    
    public boolean editproperty(Properties property){
        PreparedStatement ps;
        String editquery = "update properties set propertyType = ?, basePrice = ?, ownerId = ?, description = ?, address = ? where propertyId = ?";
        try {
            ps = ConnectionClass.getCon().prepareStatement(editquery);
            ps.setString(1, property.gettype());
            ps.setString(2, property.getprice());
            ps.setString(3, property.getownerid());
            ps.setString(4, property.getdescription());
            ps.setString(5, property.getaddress());
            ps.setInt(6, property.getpropId());
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex){
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public boolean removeproperty(int id){
        PreparedStatement ps;
        String deletequery = "delete from properties where propertyId = ?";
        try {
            ps = ConnectionClass.getCon().prepareStatement(deletequery);
            ps.setInt(1, id);
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    

    
    
    public ArrayList<Properties> propertieslist(){
        ArrayList<Properties> list = new ArrayList<>();
        Statement st;
        ResultSet rs;
        String selectquery = "select * from properties";
        try {
            st = ConnectionClass.getCon().createStatement();
            rs = st.executeQuery(selectquery);
            Properties property;
            while(rs.next()){
                property = new Properties(rs.getInt(1),
                                  rs.getString(2),
                                  rs.getString(3),
                                  rs.getString(4),
                                  rs.getString(5),
                                  rs.getString(6));
                list.add(property);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }   
}
