package oose.persistence.dao;

import oose.persistence.dto.LockerDTO;

import java.sql.*;

public class LockerDAO {
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    private Connection conn;

    private static LockerDAO dao;

    private LockerDAO(Connection conn) {
        this.conn = conn;
    }

    public static LockerDAO getInstance(Connection conn) {
        dao = new LockerDAO(conn);
        return dao;
    }

    public int insert(LockerDTO dto) {
        int resultCnt = 0;        // DB 연결 : Connection
        try {            // Statement			// SQL : Insert into
            String sql = "insert into locker (facilityname,cost) values(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getFacilityName());
            pstmt.setInt(2, dto.getCost()); // sql 실행
            resultCnt = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        // 결과
        return resultCnt;
    }

    //기관이 일치하고, memId=null 인거를 select하기
    public LockerDTO select(String facility){
        LockerDTO lockerDTO = new LockerDTO();
        int count = 0;
        String sql = "SELECT * FROM locker WHERE facilityname=? AND memId=null";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(rs.next()){
                count++;
                lockerDTO.setLockerNum(rs.getInt("locker_num"));
                lockerDTO.setFacilityName(rs.getString("facilityname"));

            }
            return lockerDTO;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        return lockerDTO;
    }

    //update
    public boolean update(LockerDTO lockerDTO){
        String sql = "update locker set member_id=?, lease_date=?, return_date=? where locker_num=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lockerDTO.getMemberId());
            pstmt.setDate(2, lockerDTO.getLeaseDate());
            pstmt.setDate(3, lockerDTO.getReturnDate());
            pstmt.setInt(4, lockerDTO.getLockerNum());

            int a = pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
