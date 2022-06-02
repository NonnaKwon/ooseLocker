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
            String jdbcUrl = "jdbc:mysql://localhost:3306/oose?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            String user = "root";
            String password = "rkwhr5461!!";

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("연결되었습니다");
            return conn;
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
