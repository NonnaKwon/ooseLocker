import oose.persistence.dao.LockerDAO;
import oose.persistence.dto.LockerDTO;
import oose.service.LockerService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        try{
            Connection conn = null;
            // 2. DB 연결 localhost == 127.0.0.1
            String jdbcUrl = "jdbc:mysql://localhost:3306/oose?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            String user = "root";
            String password = "rkwhr5461!!";

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("연결되었습니다");


            //테스트
            LockerService ls = new LockerService();
            ls.registerService("dd","123");

            //select만 잘 되면 끝임.
            System.out.println("완료");

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
