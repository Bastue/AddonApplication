package java_addon_application;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 *
 * @author Emrah Bastug
 */
public class SQL_CONNECTION {
    
    // This class will enable connection to the mysql database
    // 1 - open xampp
    // 2 - start apache and mysql
    // 3 - go to phpmyadmin -> http://localhost/phpmyadmin/
        
    // Creates a function to connect with mysql database
    public Connection createConnection()
    {
        Connection connection = null;
        
        MysqlDataSource mds = new MysqlDataSource();
        
        mds.setServerName("localhost");
        mds.setPortNumber(3306);
        mds.setUser("root");
        mds.setPassword("");
        mds.setDatabaseName("java_addon_application_db");
        
        try {
            connection = mds.getConnection();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SQL_CONNECTION.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
        
    }
    
}
