package Utilities;

import Entity.Car;
import Entity.Driver;
import Entity.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 14.11.2016.
 */
public class DB {
    private static DB instance;
    private List<Car> carsArrayList = Collections.synchronizedList(new ArrayList<Car>());
    private List<Order> ordersArrayList = Collections.synchronizedList(new ArrayList<Order>());
    private List<Driver> driversArrayList = Collections.synchronizedList(new ArrayList<Driver>());
    private List<String> massageArrayList = Collections.synchronizedList(new ArrayList<String>());

    private DB() {
    }

    public static synchronized DB getInstance(){
        if (instance == null){
            instance = new DB();
        }
        return instance;
    }

    public synchronized List<Car> getCarsArrayList() {
        return carsArrayList;
    }

    public synchronized List<Order> getOrdersArrayList() {
        return ordersArrayList;
    }

    public synchronized List<Driver> getDriversArrayList() {
        return driversArrayList;
    }

    public synchronized List<String> getMassageArrayList() {
        return massageArrayList;
    }
}
