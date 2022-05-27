package oose.persistence.dto;

import java.sql.Date;
import java.util.concurrent.locks.Lock;

public class LockerDTO {
    String lockerNum;
    String memberId;
    String facilityName;
    Date leaseDate;
    Date returnDate;
    int cost;

    public LockerDTO(){

    }
    public LockerDTO(String facilityName, int cost){
        this.facilityName = facilityName;
        this.cost = cost;
    }

    public String getLockerNum() {
        return lockerNum;
    }

    public void setLockerNum(String lockerNum) {
        this.lockerNum = lockerNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }



}
