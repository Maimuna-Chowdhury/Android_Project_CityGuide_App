package com.example.cityguide.user;

public class hosDhaka {
    String Name,Location,Number,Area,City,Type;
    public hosDhaka()
    {

    }
    public hosDhaka(String Name, String Location, String Number, String City, String Area, String Type)
    {
        this.Name=Name;
        this.Location=Location;
        this.Number=Number;
        this.City = City;
        this.Area = Area;
        this.Type = Type;
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
}
