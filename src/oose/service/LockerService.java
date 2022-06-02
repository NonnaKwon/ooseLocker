package oose.service;

import oose.persistence.dao.ConnectionProvider;
import oose.persistence.dao.LockerDAO;
import oose.persistence.dto.LockerDTO;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class LockerService {
    private final LockerDAO lockerDAO;
    private static Connection conn;

    public LockerService(){
        conn = ConnectionProvider.getConnection();
        lockerDAO = LockerDAO.getInstance(conn);
    }
    public void add(LockerDTO lockerDTO) throws Exception
    {
        lockerDAO.insert(lockerDTO);
    }

    public boolean registerService(String facility,String memId){
        ArrayList<LockerDTO> list = lockerDAO.select(facility);
        if(list == null){
            System.out.println("list null");//jsp 로 구현하기
            return false;
        }


        LockerDTO lockerDTO = new LockerDTO();
        int cursor = 0;
        while(cursor < list.size()){
            LockerDTO item = list.get(cursor);
            if(item.getMemberId()==null || item.getMemberId().equals("")){ //memID가 없으면
                lockerDTO = item;
                break;
            }else{
                cursor++;
            }
        }
        if(cursor == list.size()){ //사물함이 다 차있으면
            System.out.println("비어있는 사물함 없음"); //jsp로 구현할것
            return false;
        }

        lockerDTO.setMemberId(memId);

        Calendar cal = Calendar.getInstance();
        Date leaseDate = new Date(cal.get(Calendar.YEAR)-1900,cal.get(Calendar.MONTH),cal.get(Calendar.DATE));
        lockerDTO.setLeaseDate(leaseDate);

        Date returnDate = new Date(cal.get(Calendar.YEAR)-1900,cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE));
        lockerDTO.setReturnDate(returnDate);

        if(lockerDAO.update(lockerDTO)){
            return true;
        }else{
            return false;
        }
    }

}
