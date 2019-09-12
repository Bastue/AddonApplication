package java_addon_application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emrah Bastug
 */
public class SERVICEUSER { 

    // the service user class
    
    SQL_CONNECTION sql_connection = new SQL_CONNECTION();
    
    // create a function to add a service user
    public boolean addServiceUser(String sname, String serviceu, String four, String site)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `service`(`surname`, `service_user_type`, `four_digit_ext`, `site_or_ward_location`) VALUES (?,?,?,?)";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, sname);
            st.setString(2, serviceu);
            st.setString(3, four);
            st.setString(4, site);
            
            return (st.executeUpdate() > 0);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to edit the selected client
    public boolean editServiceUser(int id,String sname, String serviceu, String four, String site)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `service` SET `surname`=?,`service_user_type`=?,`four_digit_ext`=?,`site_or_ward_location`=? WHERE `id`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, sname);
            st.setString(2, serviceu);
            st.setString(3, four);
            st.setString(4, site);
            st.setInt(5, id);
            
            return (st.executeUpdate() > 0);
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // create a function to remove the selected client
    public boolean removeServiceUser(int id)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `service` WHERE `id`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(deleteQuery);

            st.setInt(1, id);
            
            return (st.executeUpdate() > 0);
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    // create a function to populate the jtabel with all the clients in the database
    public void fillServiceUserJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `service`";
        
        try {
            
            ps = sql_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
               row = new Object[5];
               row[0] = rs.getInt(1);
               row[1] = rs.getString(2);
               row[2] = rs.getString(3);
               row[3] = rs.getString(4);
               row[4] = rs.getString(5);
               
               tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
