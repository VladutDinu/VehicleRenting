package com.company;

import java.sql.Date;

public class Rent {
    public int ID;
    public Date Start_date;
    public Date End_date;
    public long Kilometers;
    public String PIN;
    public String RegNo;

    public Rent(int ID, Date start_date, Date end_date, long kilometers, String PIN, String RegNo) {
        this.ID = ID;
        Start_date = start_date;
        End_date = end_date;
        Kilometers = kilometers;
        this.PIN = PIN;
        this.RegNo=RegNo;
    }

}
