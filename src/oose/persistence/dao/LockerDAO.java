package oose.persistence.dao;

import oose.persistence.dto.LockerDTO;

import java.sql.*;

public class LockerDAO {
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;

    private static LockerDAO dao = new LockerDAO();

    private LockerDAO() {
    }

    public static LockerDAO getInstance() {
        return dao;
    }

    public int insert(LockerDTO dto, Connection conn) {
        int resultCnt = 0;        // DB 연결 : Connection
        try {            // Statement			// SQL : Insert into
            String sql = "insert into locker values(id,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, null);
            pstmt.setString(2, dto.getFacilityName());
            pstmt.setDate(3, null);
            pstmt.setDate(4, null);
            pstmt.setInt(5, dto.getCost()); // sql 실행
            resultCnt = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        // 결과
        return resultCnt;
    }

    //기관이 일치하고, memId=null 인거를 select하기
    public LockerDTO select(String facility,Connection conn){
        LockerDTO lockerDTO = new LockerDTO();
        String sql = "SELECT * FROM locker WHERE ";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            lockerDTO.setLockerNum(rs.getString("locker_num"));
            return lockerDTO;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        return lockerDTO;
    }

    //update
    public boolean update(LockerDTO lockerDTO,Connection conn){
        String sql = "update locker set member_id=?, lease_date=?, return_date=? where locker_num=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, lockerDTO.getMemberId());
            pstmt.setDate(2, lockerDTO.getLeaseDate());
            pstmt.setDate(3, lockerDTO.getReturnDate());
            pstmt.setString(4, lockerDTO.getLockerNum());

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
