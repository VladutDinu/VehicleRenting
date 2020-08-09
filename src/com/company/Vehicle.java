package com.company;

class Vehicle{
    public String RegNo;
    public int Year;
    public String Brand;
    public String Colour;
    public Type Type;
    public Vehicle(String RegNo, int Year, String Brand, String Colour, Type Type){
        this.RegNo=RegNo;
        this.Year=Year;
        this.Brand=Brand;
        this.Colour=Colour;
        this.Type=Type;
    }
    @Override
    public String toString(){
        return this.Brand + " " + this.RegNo + " " +this.Year;
    }
}