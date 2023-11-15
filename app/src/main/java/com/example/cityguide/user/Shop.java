package com.example.cityguide.user;

public class Shop {
    String Name,Location,Number,City,Area,Type,Price;


    public Shop() {
    }

    public Shop(String Name, String Location, String Number, String City, String Area, String Type,String Price) {
        this.Name = Name;
        this.Location = Location;
        this.Number=Number;
        this.City = City;
        this.Area = Area;
        this.Type = Type;
        this.Price=Price;

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


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
