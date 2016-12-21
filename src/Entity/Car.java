package Entity;

import Entity.Enums.CarClass;
import Entity.Enums.CarStatus;
import Utilities.TextResourceInstance;

/**
 * Created by дима on 30.10.2016.
 */
public class Car {
    private String carMark;
    private Driver carDriver;
    private String carNumber;
    private boolean isHaveBabySeat;
    private boolean isSmokeCar;
    private CarClass carClass;
    private CarStatus carStatus;
    private volatile Order order;
    //public static final int TYPE_OF_CLASS_ECONOMIC = 0;
    //public static final int TYPE_OF_CLASS_BUSYNESS = 1;
    //public static final String TYPE_OF_STATUS_FREE = "free";
    //public static final String TYPE_OF_STATUS_RESERVED = "reserver";
    private TextResourceInstance textResource = TextResourceInstance.getInstance();


    public Car(String carMark, Driver carDriver, String carNumber, boolean isHaveBabySeat, boolean isSmokeCar, CarClass carClass, CarStatus carStatus) {
        this.carMark = carMark;
        this.carDriver = carDriver;
        this.carNumber = carNumber;
        this.isHaveBabySeat = isHaveBabySeat;
        this.isSmokeCar = isSmokeCar;
        this.carClass = carClass;
        this.carStatus = carStatus;
    }

    public synchronized String getCarStatus() {
        return carStatus.toString();
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public boolean isSmokeCar() {
        return isSmokeCar;
    }

    public boolean isHaveBabySeat() {
        return isHaveBabySeat;
    }


    public synchronized void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return this.carMark + ", "+textResource.getValue("prop.key14")+" " + this.carNumber + ". "+textResource.getValue("prop.key15")+": " + this.carDriver.getName() + ", "+textResource.getValue("prop.key16")+": " + this.carDriver.getTelephone() + ".";
    }
}
