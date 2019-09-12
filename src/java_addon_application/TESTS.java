package java_addon_application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emrah Bastug
 */
public class TESTS {


    SQL_CONNECTION sql_connection = new SQL_CONNECTION();
    
    // create a function to display all test types in jtable
    public void fillTests_TYPE_JTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
        
        try {
            
            ps = sql_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
               row = new Object[3];
               row[0] = rs.getInt(1);
               row[1] = rs.getString(2);
               row[2] = rs.getString(3);
               
               tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    // create a function to display all tests in jtable
    public void fillTestsJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `tests`";
        
        try {
            
            ps = sql_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
               row = new Object[4];
               row[0] = rs.getInt(1);
               row[1] = rs.getInt(2);
               row[2] = rs.getString(3);
               row[3] = rs.getString(4);
               
               tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    // create a function to fill a combobox with the test-type id
    public void fillTests_TYPE_JCombobox(JComboBox combobox)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
        
        try {
            
            ps = sql_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {  
               combobox.addItem(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
 
    
    // create a function to add a new test
    public boolean addTest(int number, int type, String sampleid)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `tests`(`r_number`, `type`, `sample_id`, `added_on`) VALUES (?,?,?,?)";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1, number);
            st.setInt(2, type);
            st.setString(3, sampleid);
            st.setString(4, "No");
            
            return (st.executeUpdate() > 0);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
   
    // create a function to edit the selected test
    public boolean editTest(int number,int type, String sampleid, String isAddedOn)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `tests` SET `type`=?,`sample_id`=?,`added_on`=? WHERE `r_number`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(editQuery);
            
            st.setInt(1, type);
            st.setString(2, sampleid);
            st.setString(3, isAddedOn);
            st.setInt(4, number);
            
            return (st.executeUpdate() > 0);
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    // create a function to remove the selected test
    public boolean removeTest(int testNumber)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `tests` WHERE `r_number`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(deleteQuery);

            st.setInt(1, testNumber);
            
            return (st.executeUpdate() > 0);
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    // create a function to set a test to added on or not
     public boolean setTestToAddedOn(int number, String isAddedOn)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `tests` SET `added_on`=? WHERE `r_number`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, isAddedOn);
            st.setInt(2, number);
            
            return (st.executeUpdate() > 0);
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
   // create a function to check if a test is already added on
     public String isTestAddedOn(int number)
    {
        PreparedStatement st;
        ResultSet rs;
        String editQuery = "SELECT `added on` FROM `tests` WHERE `r_number`=?";
        
        try {
            
            st = sql_connection.createConnection().prepareStatement(editQuery);
            
            st.setInt(1, number);
            
            rs = st.executeQuery();
            
            if(rs.next())
            {
                return rs.getString(1);
            }
            
            else{
                return "";
            }
             
           } 
           catch (SQLException ex) {
            Logger.getLogger(SERVICEUSER.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    
}
