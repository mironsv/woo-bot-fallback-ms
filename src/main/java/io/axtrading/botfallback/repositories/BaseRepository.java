package io.axtrading.botfallback.repositories;

import java.io.Closeable;
import java.sql.*;

public abstract class BaseRepository implements Closeable {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/fallback_db?user=user&password=user";
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";


    Connection connection;  // JDBC-connection
    String tableName;

    BaseRepository(String tableName) throws SQLException {
        this.tableName = tableName;
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC driver for DataBase is not found!");
        }
        this.connection = DriverManager.getConnection(DB_URL);
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Error while closing connection!");
        }
    }

    boolean executeSqlStatement(String sql, String description) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();  // to execute sql-commands
        statement.execute(sql); // execute sql-commands
        statement.close();      // close statement to confirm table update
        if (description != null)
            System.out.println(description);
        return true;
    }

    ;

    boolean executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
        return true;
    }

    ;

    ResultSet readSQLTable(String sql) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        statement.close();
        return resultSet;
    }

    ;

    /**
     * reopens connection (if not active)
     *
     * @throws SQLException
     */
    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
    }

}
