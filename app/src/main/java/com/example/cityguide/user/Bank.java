package com.example.cityguide.user;

public class Bank {
    String Name,Location,Number,City,Area,Type;

    public Bank() {
    }

    public Bank(String name, String location, String number, String city, String area, String type) {
        Name = name;
        Location = location;
        Number = number;
        City = city;
        Area = area;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
