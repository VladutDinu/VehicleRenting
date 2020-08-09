package com.company;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

enum Type {
    Hatchback,
    Jeep,
    SUV;

}

public class Main {
    static Renter r=new Renter();
    public static void testing(){
        r.getAllVehicles("-y");
        System.out.println();
        r.getAllVehicles("-b");
        System.out.println();
        r.getVehicle(Type.Hatchback);
        System.out.println();
        r.historyOfRenting();
        r.addRent(r.vehicles.get(2),r.persons.get(4));
        System.out.println();
        r.getRentedCarsByPerson(r.persons.get(4));


    }

    public static void main(String[] args) throws Exception {

        r.addCar(new Vehicle("PH12DAL", 2005, "Audi", "Black", Type.Hatchback));
        r.addCar(new Vehicle("BV99GAL", 2004, "BMW", "Red", Type.Jeep));
        r.addCar(new Vehicle("SM14MAL", 2002, "WV", "Blue", Type.Hatchback));
        r.addCar(new Vehicle("BC31SAL", 2001, "Dacia", "Orange", Type.SUV));
        r.addCar(new Vehicle("CT45CAL", 2003, "Fiat", "Black", Type.Hatchback));

        r.addPerson(new Person("12345","Dan","Papuc","19760309","Male","19980405"));
        r.addPerson(new Person("23451","Man","Capuc","19860402","Male","19980405"));
        r.addPerson(new Person("13452","Cana","Tapuca","19790312","Female","19980405"));
        r.addPerson(new Person("12354","Fan","Bapuc","19970601","Male","19980405"));
        r.addPerson(new Person("13425","Gana","Mapuca","19840728","Female","19980405"));

        for (int i = 0; i < r.vehicles.size(); i++) {
            r.addRent(r.vehicles.get(i),r.persons.get(i));
        }

        testing();
        r.vehicles=new ArrayList<>();
        r.persons=new ArrayList<>();
        r.rented=new HashMap<>();

        System.out.println("======================================");
        List<String> vehicles= Files.readAllLines(Paths.get("vehicles.csv"));
        for(String s : vehicles) {
            String[] ss=s.split(",");
            r.vehicles.add(new Vehicle(ss[0], Integer.parseInt(ss[1]), ss[2], ss[3], Type.valueOf(ss[4])));
        }

        List<String> persons= Files.readAllLines(Paths.get("persons.csv"));
        for(String s : persons) {
            String[] ss=s.split(",");
            r.persons.add(new Person(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5]));
        }

        for (int i = 0; i < r.vehicles.size(); i++) {
            r.addRent(r.vehicles.get(i),r.persons.get(i));
        }

        testing();

       /* System.out.println('\n'+"======JDBC part======"+'\n');
        DBConnector connector=new DBConnector();
        if(connector.hasDb!=1)
             connector.createDB();
        connector.connect();
        if(connector.hasVehicles!=1)
            connector.createTableVehicles();
        if(connector.hasPersons!=1)
            connector.createTablePersons();
        if(connector.hasRents!=1)
            connector.createTableRents();

       // for(Vehicle v : r.vehicles)
       //     connector.addVehicle(v);
       // for(Person p : r.persons)
       //     connector.addPerson(p);
       // BigDecimal kilo=new BigDecimal(120000);
       // for(Map.Entry<Vehicle, Person> it : r.rented.entrySet())
        //    connector.addRent(it.getValue(), it.getKey(), java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now().plusMonths(2).plusDays(4).plusYears(1)), (kilo=kilo.multiply(new BigDecimal("1.1"))));
        System.out.println('\n'+"=====HISTORY OF RENTING=====");
        connector.getHistory(r.persons,r.vehicles);
        System.out.println('\n'+"=====HISTORY OF RENTING BY A PERSON=====");
        connector.getHistoryByPerson(r.persons.get(4));*/
    }
}
