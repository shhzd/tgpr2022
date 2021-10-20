package tgpr.moudeule.model;

import java.sql.*;

public abstract class Model {
    protected static Connection db;

    static {
        try {
            db = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tgpr-2122-z03?user=root&password=root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDb() {
        try {
            if (db == null)
                return false;
            return db.isValid(0);   // 0 pour ne pas utiliser de timeout
        } catch (SQLException e) {
            return false;
        }
    }

    public int getLastInsertId(Statement st) throws SQLException {
        ResultSet keys = st.getGeneratedKeys();
        if (keys.next())
            return keys.getInt(1);
        return 0;
    }

}