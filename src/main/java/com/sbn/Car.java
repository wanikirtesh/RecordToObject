package com.sbn;

import java.util.ArrayList;
import java.util.List;

public class Car {
    @DBTable(columnName = "model")
    private String model;
    @DBTable(columnName = "color")
    private String color;
    @DBTable(columnName = "mfdDate")
    private int mfdYear;

    @DBTable(columnName = "carId")
    private int carId;

    private List<Owner> ownerList = new ArrayList<>();

    public Car(int carId,String model, String color, int mfdYear) {
        this.carId = carId;
        this.model = model;
        this.color = color;
        this.mfdYear = mfdYear;
    }

    public Car(){
        
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMfdYear() {
        return mfdYear;
    }

    public void setMfdYear(int mfdYear) {
        this.mfdYear = mfdYear;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
   public boolean equals(Object car){
        Car cr = (Car) car;
        return this.color.equals(cr.color) && this.model.equals(cr.model) && this.mfdYear == cr.mfdYear && cr.ownerList.equals(this.ownerList) && this.carId == cr.carId;
    }
    @Override
    public String toString(){
        String owners =" Owners: [";
        for (Owner owner : ownerList) {
            owners += ("{ownerId:"+ owner.getOwnerId() +" name:" + owner.getName() + " birthDate:" + owner.getCity() + " carId:" +  owner.getCarId() + "},");
        }
        owners += "]";
        return "model:" + this.model +" color:"+this.color + " mfdYear:" + this.mfdYear + owners;
    }
}
