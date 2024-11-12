package murach.data;

import java.sql.*;
import murach.business.User;

public class UserDB {

    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance(); // Fixed typo: getlnstance -> getInstance
        Connection connection = pool.getConnection(); // Fixed typo: connection = pool-getConnection -> pool.getConnection()
        PreparedStatement ps = null;
        String query = "INSERT INTO User (Email, FirstName, LastName) "
                + "VALUES (?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail()); // Fixed typo: setstring -> setString
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e); // Fixed typo: printIn -> println
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance(); // Fixed typo: getlnstance -> getInstance
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE User SET FirstName = ?, LastName = ? WHERE Email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName()); // Fixed typo: setstring -> setString
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e); // Fixed typo: printIn -> println
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static User selectUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance(); // Fixed typo: getlnstance -> getInstance
        Connection connection = pool.getConnection(); // Fixed typo: pool- getConnection -> pool.getConnection()
        PreparedStatement ps = null;
        ResultSet rs = null; // Fixed initialization: rs null -> rs = null
        User user = null; // Initialize user

        String query = "SELECT * FROM User WHERE Email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery(); // Fixed assignment: = ps.executeQuery() -> rs = ps.executeQuery()

            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Email FROM User WHERE Email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next(); // If a record is found, the email exists
        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }


}

