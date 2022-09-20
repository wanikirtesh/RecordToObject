package com.sbn;

public class Car {
    @DBTable(columnName = "model")
    private String model;
    @DBTable(columnName = "color")
    private String color;
    @DBTable(columnName = "mfdDate")
    private int mfdYear;

    public Car(String model, String color, int mfdYear) {

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

   @Override
   public boolean equals(Object car){
        Car cr = (Car) car;

        return this.color.equals(cr.color) && this.model.equals(cr.model) && this.mfdYear == cr.mfdYear;
    }

    @Override
    public String toString(){
        return "model:" + this.model +"color:"+this.color + "mfdYear:" + this.mfdYear;
    }
}
