package com.sbn;

import java.sql.Date;

public class Owner {

    @DBTable(columnName = "ownerid")
    private int ownerId;
    @DBTable(columnName = "name")
    private String name;

    @DBTable(columnName = "carId")
    private int carId;

    @DBTable(columnName = "city")
    private String city;

    public Owner(int ownerId,String name, String city, int carId) {
        this.ownerId=ownerId;
        this.name = name;
        this.city = city;
        this.carId = carId;
    }

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object owner){
        Owner own = (Owner) owner;
        return own.carId == this.carId && this.name.equals(own.name) && this.city.equals(own.city) && this.ownerId==own.ownerId;
    }

    @Override
    public String toString(){
        return "name:" + this.name + " birthDate:" + this.city + " carId:" + this.carId + " ownerId:" + this.ownerId;
    }
}
