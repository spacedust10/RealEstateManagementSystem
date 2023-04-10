
import java.sql.*;
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
public class Sale {
    private int id;
    private int propid;
    private int clientid;
    private String fprice;
    private String sdate;
    
    public Sale(){};
    public Sale(int id, int propid, int clientid, String fprice, String date){
        this.id = id;
        this.propid = propid;
        this.clientid = clientid;
        this.fprice = fprice;
        this.sdate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropid() {
        return propid;
    }

    public void setPropid(int propid) {
        this.propid = propid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getFprice() {
        return fprice;
    }

    public void setFprice(String fprice) {
        this.fprice = fprice;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
    
    public boolean addNEWsale(Sale sale){
        PreparedStatement ps;
        String addquery = "insert into sales (propid,clientid,fprice,sdate) values(?,?,?,?)";
        try {
            ps = ConnectionClass.getCon().prepareStatement(addquery);
            ps.setInt(1, sale.getPropid());
            ps.setInt(2, sale.getClientid());
            ps.setString(3, sale.getFprice());
            ps.setString(4, sale.getSdate());
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean editsale(Sale sale){
        PreparedStatement ps;
        String editquery = "update sales set propid = ?, clientid = ?, fprice = ?, sdate = ? where saleid = ?";
        try {
            ps = ConnectionClass.getCon().prepareStatement(editquery);
            ps.setInt(1, sale.getPropid());
            ps.setInt(2, sale.getClientid());
            ps.setString(3, sale.getFprice());
            ps.setString(4, sale.getSdate());
            ps.setInt(5, sale.getId());
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deletesale(int id){
        PreparedStatement ps;
        String deletequery = "delete from sales where saleid = ?";
        try {
            ps = ConnectionClass.getCon().prepareStatement(deletequery);
            ps.setInt(1, id);
            
            return (ps.executeUpdate() > 0);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<Sale> saleList(){
        ArrayList<Sale> list = new ArrayList<>();
        Statement st;
        ResultSet rs;
        String selectquery = "select * from sales";
        try {
            st = ConnectionClass.getCon().createStatement();
            rs = st.executeQuery(selectquery);
            Sale sale;
            while(rs.next()){
                sale = new Sale(rs.getInt(1),
                                  rs.getInt(2),
                                  rs.getInt(3),
                                  rs.getString(4),
                                  rs.getString(5));
                list.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
}
