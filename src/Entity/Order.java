package Entity;

/**
 * Created by дима on 30.10.2016.
 */
public class Order {
    private String startPoint;
    private String endPoint;
    private boolean isNeedBabySeat;
    private boolean isNeedSmokeCar;
    private int needCarClass;
    private volatile Car carReserver;
    private volatile String orderStatus;
    public static String TYPE_OF_ORDER_STATUS_NEW_ORDER = "NewOrder";
    public static String TYPE_OF_ORDER_STATUS_WAIT_ORDER = "WaitOrder";
    public static String TYPE_OF_ORDER_STATUS_IN_WORK_ORDER = "InWorkOrder";
    public static String TYPE_OF_ORDER_STATUS_CLOSET_ORDER = "ClosetOrder";
    public static String TYPE_OF_ORDER_STATUS_CANSEL_ORDER = "CanselOrder";

    public Order(String startPoint, String endPoint, boolean isNeedBabySeat, boolean isNeedSmokeCar, int needCarClass) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.isNeedBabySeat = isNeedBabySeat;
        this.isNeedSmokeCar = isNeedSmokeCar;
        this.needCarClass = needCarClass;
        this.carReserver = null;
        this.orderStatus = Order.TYPE_OF_ORDER_STATUS_NEW_ORDER;
    }

    public boolean isNeedBabySeat() {
        return isNeedBabySeat;
    }

    public boolean isNeedSmokeCar() {
        return isNeedSmokeCar;
    }

    public int getNeedCarClass() {
        return needCarClass;
    }

    public synchronized Car getCarReserver() {
        return carReserver;
    }

    public synchronized void setCarReserver(Car carReserver) {
        this.carReserver = carReserver;
    }

    public synchronized String getOrderStatus() {
        return orderStatus;
    }

    public synchronized void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        String orderedCar = (this.carReserver == null) ? ", машина не назначена" : ", " + this.carReserver.toString();
        String orderedType;
        if (this.needCarClass == Car.TYPE_OF_CLASS_BUSYNESS) orderedType = "Бизнес класс";
        else if (this.needCarClass == Car.TYPE_OF_CLASS_ECONOMIC) orderedType = "Эконом класс";
        else orderedType = "Не присвоен";
        String orderStatus;
        if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_NEW_ORDER) orderStatus = "новый заказ";
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_WAIT_ORDER) orderStatus = "ожидает машину";
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_IN_WORK_ORDER) orderStatus = "в работе";
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_CLOSET_ORDER) orderStatus = "закрыт";
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_CANSEL_ORDER) orderStatus = "отменен";
        else orderStatus = "Не присвоен";
        return "Статус заказа: "+orderStatus+". Пункт отправки: " + this.startPoint + ", конечная точка: " + this.endPoint + ", необходимо детское сидение: " + this.isNeedBabySeat + ", необходим салон для курящих: " + this.isNeedSmokeCar + ", класс машины: " + orderedType + orderedCar;
    }
}
