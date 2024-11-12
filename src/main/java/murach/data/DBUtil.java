package murach.data;

import java.sql.*;

public class DBUtil {

    public static void closeStatement(Statement s) { // Fixed typo: closestatement -> closeStatement
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException e) {
            System.out  .println(e); // Fixed typo: printIn -> println
        }
    }

    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e); // Fixed typo: printIn -> println
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e); // Fixed typo: printIn -> println
        }
    }
}
