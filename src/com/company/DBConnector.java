package com.company;

import javax.swing.plaf.nimbus.State;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
    Connection c;
    public int hasDb;
    public int hasVehicles;
    public int hasPersons;
    public int hasRents;

    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String DB_URL = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";
    static final String DB_NAME="RentingCompany";
    static final String DB_VEHICLES="Vehicles";
    static final String DB_PERSONS="Persons";
    static final String DB_RENTS="Rents";

    public void connect(){
        try{
            System.out.println("Connecting...");
            Connection conn= DriverManager.getConnection(DB_URL);
            c=conn;
            System.out.println("Connect!");
            Statement stmt = conn.createStatement();


        }
        catch(SQLException e) {
            throw new IllegalStateException("Cannot connect to db",e);
        }
        try {
            System.out.println("Load driver...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Loaded driver");
        }
        catch(ClassNotFoundException e)
        {
            throw new IllegalStateException("Cannot find driver",e);
        }
    }
    public void createDB(){
        Connection con = null;
        Statement stmt = null;

        try{
            con= DriverManager.getConnection(DB_URL);
            if(con != null) {
                System.out.println("Check if a database exists using java");
                ResultSet rs = con.getMetaData().getCatalogs();

                while (rs.next()) {
                    String catalogs = rs.getString(1);
                    if(DB_NAME.equals(catalogs))
                        hasDb=1;
                }
                if(hasDb!=1) {
                    System.out.println("Creating database...");
                    stmt = con.createStatement();
                    String sql = "CREATE DATABASE " + DB_NAME;
                    stmt.executeUpdate(sql);
                    System.out.println("Database created successfully...");
                    this.DB_URL+="databaseName=RentingCompany";
                    hasDb=1;
                    return;
                }
                else{
                        System.out.println("The database " + DB_NAME + " already exists.");
                        this.DB_URL+="databaseName=RentingCompany";
                        return;
                }
            }
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createTableVehicles(){
        try {
            Connection conn= DriverManager.getConnection(DB_URL);
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs=dbm.getTables(null,null, DB_VEHICLES.toUpperCase(),null);
            while(rs.next()){
                if(DB_VEHICLES.equals(rs.getString(3)))
                {
                    hasVehicles=1;
                    System.out.println("Table "+ DB_VEHICLES +" already exists.");
                    return;
                }
            }
            if(hasVehicles!=1){
                hasVehicles=1;
                System.out.println("Creating table in given database...");
                Statement stmt = conn.createStatement();

                String sql = "CREATE TABLE Vehicles " +
                        "(Id INTEGER not NULL IDENTITY(1,1), " +
                        " RegNo VARCHAR(255), " +
                        " Year INTEGER, " +
                        " Brand VARCHAR(255), " +
                        " Colour VARCHAR(255), "+
                        " Type VARCHAR(255), "+
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");
            }
            else{
                System.out.println("Table exists");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addVehicle(Vehicle v){
        try{
            Connection conn= DriverManager.getConnection(DB_URL);
            String sqlQuery="Insert into Vehicles (RegNo,Year,Brand,Colour,Type) values(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sqlQuery);
            pst.setString(1, v.RegNo);
            pst.setInt(2, v.Year);
            pst.setString(3, v.Brand);
            pst.setString(4, v.Colour);
            pst.setString(5, v.Type.name());
            System.out.println("1231312213");
            Statement stmt= conn.createStatement();
            pst.executeUpdate();
            conn.commit();
            conn.close();
            System.out.println("Added a car");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createTablePersons(){
        try {
            Connection conn= DriverManager.getConnection(DB_URL);
            ResultSet rs=(conn.getMetaData()).getTables(null,null, DB_PERSONS.toUpperCase(),null);
            while(rs.next()) {
                if (DB_PERSONS.equals(rs.getString(3))) {
                    hasPersons = 1;
                    System.out.println("Table "+ DB_PERSONS +" already exists.");
                    return;
                }
            }
            if(hasPersons!=1){
                hasPersons=1;
                System.out.println("Creating table in given database...");
                Statement stmt = conn.createStatement();

                String sql = "CREATE TABLE Persons " +
                        "(Id INTEGER not NULL IDENTITY(1,1), " +
                        " PIN VARCHAR(255), " +
                        " FirstName VARCHAR(255), " +
                        " LastName VARCHAR(255), "+
                        " DateOfBirth VARCHAR(255), "+
                        " Gender VARCHAR(255), "+
                        " DrivingLicenseYear VARCHAR(255), "+
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");
            }
            else{
                System.out.println("Table exists");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addPerson(Person v){
        try{
            Connection conn= DriverManager.getConnection(DB_URL);
            String sqlQuery="Insert into Persons (PIN,FirstName,LastName,DateOfBirth,Gender,DrivingLicenseYear) values(?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sqlQuery);
            pst.setString(1, v.PIN);
            pst.setString(2, v.FirstName);
            pst.setString(3, v.LastName);
            pst.setString(4, v.DateOfBirth);
            pst.setString(5, v.Gender);
            pst.setString(6, v.DrivingLicenseYear);
            Statement stmt= conn.createStatement();
            pst.executeUpdate();
            conn.commit();
            conn.close();
            System.out.println("Added a person");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createTableRents(){
        try {
            Connection conn= DriverManager.getConnection(DB_URL);
            ResultSet rs=(conn.getMetaData()).getTables(null,null, DB_RENTS.toUpperCase(),null);
            while(rs.next()) {
                if (DB_RENTS.equals(rs.getString(3))) {
                    hasRents = 1;
                    System.out.println("Table "+ DB_RENTS +" already exists.");
                    return;
                }
            }
            if(hasRents!=1){
                hasRents=1;
                System.out.println("Creating table in given database...");
                Statement stmt = conn.createStatement();

                String sql = "CREATE TABLE Rents " +
                        "(Id INTEGER not NULL IDENTITY(1,1), " +
                        " Start_date DATETIME not NULL, " +
                        " End_date DATETIME not NULL, " +
                        " Kilometers BIGINT, "+
                        " PIN VARCHAR(255), "+
                        " RegNO VARCHAR(255), "+
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");
            }
            else{
                System.out.println("Table exists");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addRent(Person p, Vehicle v, Date startdate, Date enddate, BigDecimal Kilometers){
        try{
            Connection conn= DriverManager.getConnection(DB_URL);
            String sqlQuery="Insert into Rents (Start_date,End_date,Kilometers,PIN,RegNo) values(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sqlQuery);
            pst.setDate(1, startdate);
            pst.setDate(2, enddate);
            pst.setBigDecimal(3, BigDecimal.valueOf(Kilometers.longValue()));
            pst.setString(4, p.PIN);
            pst.setString(5, v.RegNo);
            Statement stmt= conn.createStatement();
            pst.executeUpdate();
            conn.commit();
            conn.close();
            System.out.println("Added a rent");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void getHistory(List<Person> p, List<Vehicle> v) throws Exception {
        Connection conn= DriverManager.getConnection(DB_URL);
        Statement stm= conn.createStatement();
        String sqlQuerry="SELECT Id, Start_date, End_date, Kilometers, PIN, RegNo FROM Rents";
        ResultSet rs=stm.executeQuery(sqlQuerry);
        while(rs.next()) {
            System.out.print(rs.getInt(1) + " " + rs.getDate(2) + " " + rs.getDate(3)+" ");
            for(Person _p : p)
                if(_p.PIN.compareTo(rs.getString(5))==0)
                    System.out.print(_p.FirstName + " " + _p.LastName+" ");
            for(Vehicle _v : v)
                if(_v.RegNo.compareTo(rs.getString(6))==0)
                    System.out.print(_v.Brand+"\n");

        }
        conn.close();
    }
    public void getHistoryByPerson(Person p) throws Exception{
        Connection conn= DriverManager.getConnection(DB_URL);
        Statement stm= conn.createStatement();
        Statement stm1= conn.createStatement();
        String sqlQuerry="Select * from Persons inner join Rents on Persons.PIN=Rents.PIN Where Persons.PIN="+p.PIN;
        ResultSet rs=stm.executeQuery(sqlQuerry);
        while(rs.next()) {
            System.out.print(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " ");
            sqlQuerry="Select * from Vehicles inner join Rents on Vehicles.RegNo=Rents.RegNO WHERE Vehicles.RegNo="+"'"+rs.getString(13)+"'";
            ResultSet rs1=stm1.executeQuery(sqlQuerry);
            while(rs1.next())
                System.out.println(rs1.getString(4)+" "+rs1.getString(2));
        }
        conn.close();
    }

}
