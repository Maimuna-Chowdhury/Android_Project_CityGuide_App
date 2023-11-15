package com.example.cityguide.user;

import android.net.Uri;

public class resDhaka {
    String Name,Location,Number,Area,City,Type,Price;
    Float Rating;

    public resDhaka()
    {

    }
    public resDhaka(String Name,String Location,String Number,String Area,String City,String Type,String Price,Float Rating)
    {
        this.Name=Name;
        this.Location=Location;
        this.Number=Number;
        //this.imageUrl=imageUrl;
        this.Area=Area;
        this.City=City;
        this.Type=Type;
        this.Rating=Rating;
        this.Price=Price;
      //  this.Image=Image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
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

    public Float getRating() {
        return Rating;
    }

    public void setRating(Float rating) {
        Rating = rating;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
