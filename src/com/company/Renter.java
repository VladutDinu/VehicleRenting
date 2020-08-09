package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Renter implements Renting{
    public List<Person> persons;
    public List<Vehicle> vehicles;
    public HashMap<Vehicle,Person> rented=new HashMap<>();
    public Renter(List<Person> p, List<Vehicle> v){
        this.persons=p;
        this.vehicles=v;
    }
    public Renter(){
        this.persons=new ArrayList<>();
        this.vehicles=new ArrayList<>();
    }
    public void addCar(Vehicle car){
        vehicles.add(car);
    }
    public void addPerson(Person per){
        persons.add(per);
    }
    public void addRent(Vehicle car, Person per){
        rented.put(car,per);
    }
    @Override
    public void getAllVehicles(String optionOfSorting) {
        if(optionOfSorting.compareTo("-y")==0) {
            vehicles.stream().sorted((v1,v2) -> v1.Year - v2.Year).forEach(System.out::println);
        }
        else if(optionOfSorting.compareTo("-b")==0){
            vehicles.stream().sorted((v1,v2) -> v1.Brand.compareTo(v2.Brand)).forEach(System.out::println);
        }
        else{
            System.out.println("Wrong option of showing all the vehicles. Please enter as parameter -y or -b");
        }
    }

    @Override
    public void getVehicle(Type t) {
        vehicles.stream().filter(v -> v.Type==t).forEach(System.out::println);
    }

    @Override
    public void historyOfRenting() {
        rented.forEach((K,V) -> System.out.println("Car : " + K.toString() + "   Person : "+ V.toString()));
    }

    @Override
    public void rent(Vehicle v, Person p) {
        rented.put(v, p);
    }

    @Override
    public void getRentedCarsByPerson(Person p) {
        rented.forEach((K,V) ->{
            if(V.equals(p))
                System.out.println(K.toString());
        });
    }
}
