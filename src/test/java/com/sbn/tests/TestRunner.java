package com.sbn.tests;

import com.sbn.Car;
import com.sbn.ObjectConverter;
import com.sbn.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestRunner {


    @Test
    public void simpleTest() {
        List<Car> carList = ObjectConverter.getObjectListFromDbQuery(Car.class,"Select * from Car");
        for (Car car : carList) {
            car.setOwnerList(ObjectConverter.getObjectListFromDbQuery(Owner.class,"select * from owners where carId=" + car.getCarId()));
        }

        List<Car> cars = ObjectConverter.getObjectListFromJsonArray(Car.class,getCarResponse());
        Assert.assertEquals(cars,carList);
    }



    private static String getCarResponse() {
        return "[{\"carId\":1,\"model\":\"I20\",\"color\":\"red\",\"mfdYear\":2005,\"ownerList\":[{\"ownerId\":1,\"name\":\"Kirtesh\",\"city\":\"Shahada\",\"carId\":1},{\"ownerId\":2,\"name\":\"Gopal\",\"city\":\"Shahada\",\"carId\":1}]}]";
    }
}
