package oose.persistence.dao;

import oose.persistence.dto.LockerDTO;

import java.sql.*;
import java.util.ArrayList;

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
//            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        // 결과
        return resultCnt;
    }

    //기관이 일치하고, memId=null 인거를 select하기
    public ArrayList<LockerDTO> select(String facility){
        ArrayList<LockerDTO> list = new ArrayList<>();
        try {
            String sql = "select * from locker where facilityname=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,facility);
            rs = pstmt.executeQuery();

            while(rs.next()){
                LockerDTO lockerDTO = new LockerDTO();
                lockerDTO.setLockerNum(rs.getInt("locker_num"));
                lockerDTO.setMemberId(rs.getString("member_id"));
                lockerDTO.setFacilityName(rs.getString("facilityname"));
                list.add(lockerDTO);
            }

            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        return list;
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
//            try {
//                pstmt.close();
//                conn.close();
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

        }

    }

}
