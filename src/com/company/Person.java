package com.company;

import java.util.Objects;

class Person{
    public String PIN;
    public String FirstName;
    public String LastName;
    public String DateOfBirth;
    public String Gender;
    public String DrivingLicenseYear;
    Person(String PIN, String FirstName, String LastName, String DateOfBirth, String Gender, String Driving){
        this.PIN=PIN;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.DateOfBirth=DateOfBirth;
        this.Gender=Gender;
        this.DrivingLicenseYear=Driving;
    }
    @Override
    public String toString(){
        return this.FirstName + " " + this.LastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(PIN, person.PIN) &&
                Objects.equals(FirstName, person.FirstName) &&
                Objects.equals(LastName, person.LastName) &&
                Objects.equals(DateOfBirth, person.DateOfBirth) &&
                Objects.equals(Gender, person.Gender) &&
                Objects.equals(DrivingLicenseYear, person.DrivingLicenseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PIN, FirstName, LastName, DateOfBirth, Gender, DrivingLicenseYear);
    }
}