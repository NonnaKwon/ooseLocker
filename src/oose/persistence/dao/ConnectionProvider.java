package oose.persistence.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    public static Connection getConnection()
    {
        try{
            Connection conn = null;
            // 2. DB 연결 localhost == 127.0.0.1
            String jdbcUrl = "jdbc:mysql://localhost/oose?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            String user = "root";
            String password = "rkwhr5461!!";
            conn = DriverManager.getConnection(jdbcUrl, user, password);
            return conn;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
