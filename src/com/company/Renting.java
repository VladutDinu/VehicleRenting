package com.company;

interface Renting{
    void getAllVehicles(String o);
    void getVehicle(Type t);
    void historyOfRenting();
    void rent(Vehicle v, Person p);
    void getRentedCarsByPerson(Person p);

}