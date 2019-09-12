package java_addon_application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emrah Bastug
 */
public class REQUESTS {
    
    // The following FOREIGN KEYS are needed to link up the requests when displayed to the user:
    
    // 1 for the service user 
    // -> alter service requests ADD CONSTRAINT fk_service_user_id FOREIGN KEY (service_user_id) REFERENCES service(id) on DELETE CASCADE
    
    // 2 for the test 
    // -> alter service requests ADD CONSTRAINT fk_test_number FOREIGN KEY (test_number) REFERENCES tests(r_number) on DELETE CASCADE
        
    // Another foreign key between types and tests   
    // -> alter tests tests ADD CONSTRAINT fk_type_id FOREIGN KEY (type) REFERENCES type(id) on DELETE CASCADE
   
       
    SQL_CONNECTION sql_connection = new SQL_CONNECTION();
    
    TESTS test = new TESTS();
    
    // create a function to add a new room
    public boolean addRequests(int service_user_id, int test_number)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `requests`(`service_user_id`, `test_number`) VALUES (?,?)";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1, service_user_id);
            st.setInt(2, test_number);
            
                       
            return (st.executeUpdate() > 0);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
           
    
    // create a function to edit the selected room
    public boolean editRequests(int requests_id, int service_user_id, int test_number)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `requests` SET `service_user_id`=?,`test_number`=? WHERE `id`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(editQuery);
            
            st.setInt(1, requests_id);
            st.setInt(1, service_user_id);
            st.setInt(2, test_number);
            
            
            
            return (st.executeUpdate() > 0);
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
     // create a function to remove the selected request
    public boolean removeRequests(int request_id)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `requests` WHERE `id`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(deleteQuery);

            st.setInt(1, request_id);
            
            
            return (st.executeUpdate() > 0);
                            
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // BELOW MIGHT BE DELETED!"!!!! EMRAH ARGGGH
     // create a function to display all rooms in jtable
    public void fillRequestsJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `requests`";
        
        try {
            
            ps = sql_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
               row = new Object[3];
               row[0] = rs.getInt(1);
               row[1] = rs.getInt(2);
               row[2] = rs.getInt(3);
               
               
               tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   
    
    // create a function to get the room number from a request
    public int getTestNumberFromRequest(int requestID)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT `test_number` FROM `requests` WHERE `id` = ?";
        
        try {
            
            ps = sql_connection.createConnection().prepareStatement(selectQuery);
            
            ps.setInt(1, requestID);
            
            rs = ps.executeQuery();
            
            if(rs.next()) 
            {
                return rs.getInt(1);
            }
            else{
                return 0;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } 
    }

    boolean addRequest(int service_user_id, int test_number, java.util.Date date_in, java.util.Date date_out) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}



