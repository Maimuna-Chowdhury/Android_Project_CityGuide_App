package com.example.cityguide.user;

public class SearchPlace {
    String Name,Location,Number,Area,City,Type,Price;

    public SearchPlace() {
    }

    public SearchPlace(String name, String location, String number, String area, String city, String type,String price) {
        Name = name;
        Location = location;
        Number = number;
        Area = area;
        City = city;
        Type = type;
        Price=price;
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

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
