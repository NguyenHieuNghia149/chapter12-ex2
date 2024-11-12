package murach.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/murach_test");
        } catch (NamingException e) {
            System.out.println(e); // Fixed typo: printIn -> println
        }
    }



    public static synchronized ConnectionPool getInstance() { // Fixed typo: getlnstance -> getInstance
        if (pool == null) { // Fixed typo: = = -> ==
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() { // Fixed typo: getConnectionO -> getConnection
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e); // Fixed typo: printIn -> println
            return null;
        }
    }

    public void freeConnection(Connection c) {
        if (c != null) { // Check if the connection is not null
            try {
                c.close(); // Close the connection if it's valid
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage()); // Log any exceptions during the closing process
            }
        } else {
            System.out.println("Warning: Attempted to close a null connection.");
        }
    }
}
