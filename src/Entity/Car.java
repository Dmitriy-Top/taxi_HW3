package Entity;

/**
 * Created by дима on 30.10.2016.
 */
public class Car {
    private String carMark;
    private Driver carDriver;
    private String carNumber;
    private boolean isHaveBabySeat;
    private boolean isSmokeCar;
    private int carClass;
    private volatile String carStatus;
    private volatile Order order;
    public static final int TYPE_OF_CLASS_ECONOMIC = 0;
    public static final int TYPE_OF_CLASS_BUSYNESS = 1;
    public static final String TYPE_OF_STATUS_FREE = "Свободен";
    public static final String TYPE_OF_STATUS_RESERVED = "Занят";


    public Car(String carMark, Driver carDriver, String carNumber, boolean isHaveBabySeat, boolean isSmokeCar, int carClass, String carStatus) {
        this.carMark = carMark;
        this.carDriver = carDriver;
        this.carNumber = carNumber;
        this.isHaveBabySeat = isHaveBabySeat;
        this.isSmokeCar = isSmokeCar;
        this.carClass = carClass;
        this.carStatus = carStatus;
    }

    public synchronized String getCarStatus() {
        return carStatus;
    }

    public int getCarClass() {
        return carClass;
    }

    public boolean isSmokeCar() {
        return isSmokeCar;
    }

    public boolean isHaveBabySeat() {
        return isHaveBabySeat;
    }


    public synchronized void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return this.carMark + ", номер " + this.carNumber + ". Водитель: " + this.carDriver.getName() + ", телефон для связи: " + this.carDriver.getTelephone() + ".";
    }
}
