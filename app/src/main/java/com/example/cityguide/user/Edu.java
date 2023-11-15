package com.example.cityguide.user;

public class Edu {
    String Name,Number,City,Area,Type,Ranking,Location;

    public Edu() {
    }

    public Edu(String Name, String Number, String City, String Area, String Type, String Ranking,String Location) {
        this.Name = Name;
        this.Number = Number;
        this.City = City;
        this. Area = Area;
        this.Type = Type;
        this.Ranking = Ranking;
        this.Location=Location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getRanking() {
        return Ranking;
    }

    public void setRanking(String ranking) {
        Ranking = ranking;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}

