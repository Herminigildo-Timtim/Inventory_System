/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author hermi
 */
public class Connect {
    
    Connection conn = null;
    
    public Connect(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinventorysystem","root","");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection(){
        return conn;
    }
    
    public ArrayList<Inventory> displayInventory(String tableName) throws SQLException{
        ArrayList<Inventory> inventory = new ArrayList<>();
        String sql ="select * from inventory";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Inventory a;
              a = new Inventory(rs.getInt(1),rs.getString(3));
              inventory.add(a);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventory;
    }
    
    public ArrayList<Item1> displayItem(){
        ArrayList<Item1> item = new ArrayList<>();
        String sql ="select * from item";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Item1 a;
              a = new Item1(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
              item.add(a);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return item;
    }
    
    public ArrayList<Item1> displaySearchItem(String searchItem) throws SQLException {
    ArrayList<Item1> item = new ArrayList<>();
    boolean flag = false;
    try {
        int id = Integer.parseInt(searchItem);
        String sql = "select * from item where itemID = '" + id + "' ";
        Statement stmt;
        ResultSet rs;

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Item1 a;
            a = new Item1(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
            item.add(a);
            flag = true;
        }
        if(flag){
            JOptionPane.showMessageDialog(null, "Item is found");
        }else{
            JOptionPane.showMessageDialog(null, "Item not found");
        }
    } catch (NumberFormatException e) {
        String sql = "select * from item where itemName like '%" + searchItem + "%'";
        Statement stmt;
        ResultSet rs;

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Item1 a;
            a = new Item1(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
            item.add(a);
            flag = true;
        }
        if(flag){
            JOptionPane.showMessageDialog(null, "Item is found");
        }else{
            
            JOptionPane.showMessageDialog(null, "Item not found");
        }
    } catch (SQLException e) {
        Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
    }
    return item; // Return the 'item' ArrayList instead of 'null'
}
    
    public void updateItem(String origname, String origDescription,int origQuantity,String name, String description1, int quantity1) {
    String sql1 = "SELECT * FROM item WHERE itemname = '" + name + "'";
    String sql = "UPDATE item SET itemname = '" + name + "', itemdescription = '" + description1 + "', itemquantity = " + quantity1 + " WHERE itemname = '" + origname + "'";
    Statement stmt;

    try {
        stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql1);
        
        
        if(origname.equals(name) && (origDescription == null || origDescription.equals(description1)) && origQuantity == quantity1){
            JOptionPane.showMessageDialog(null, "Nothing changed");
        }
        else if (!resultSet.next() || origname.equals(name)) { // If there's no record with the new name, proceed with the update
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Item updated!");
        }else{
            JOptionPane.showMessageDialog(null, "Name already exist!");
        }

    } catch (SQLException ex) {
        Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
    }
}      
}
