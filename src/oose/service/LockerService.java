package oose.service;

import oose.persistence.dao.ConnectionProvider;
import oose.persistence.dao.LockerDAO;
import oose.persistence.dto.LockerDTO;

import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

public class LockerService {
    private final LockerDAO lockerDAO = LockerDAO.getInstance();
    private static Connection conn;

    public LockerService(){
        conn = ConnectionProvider.getConnection();
    }
    public void add(LockerDTO lockerDTO) throws Exception
    {
        lockerDAO.insert(lockerDTO,conn);
    }

    public boolean registerService(String facility,String memId){
        LockerDTO lockerDTO = lockerDAO.select(facility,conn);
        lockerDTO.setFacilityName(facility);
        lockerDTO.setMemberId(memId);

        Calendar cal = Calendar.getInstance();
        Date leaseDate = new Date(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));
        lockerDTO.setLeaseDate(leaseDate);

        Date returnDate = new Date(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE));
        lockerDTO.setReturnDate(returnDate);

        if(lockerDAO.update(lockerDTO,conn)){
            return true;
        }else{
            return false;
        }
    }

}
