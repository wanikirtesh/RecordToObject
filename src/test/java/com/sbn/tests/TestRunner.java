package com.sbn.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbn.Car;
import com.sbn.ObjectConverter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestRunner {


    @Test
    public void simpleTest() throws JsonProcessingException {
        List<Car> carList = ObjectConverter.getObjectListFromDbQuery(Car.class,"Select * from Car");
        List<Car> cars = ObjectConverter.getObjectListFromJsonArray(Car.class,getCarResponse());
        Assert.assertEquals(cars,carList);
    }



    private static String getCarResponse() {
        return "[{\"model\":\"I20\",\"color\":\"red\",\"mfdYear\":2007}]";
    }
}
