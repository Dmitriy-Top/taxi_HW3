package Entity;

import Entity.Enums.CarClass;
import Utilities.TextResourceInstance;

/**
 * Created by дима on 30.10.2016.
 */
public class Order {
    static private int idCounter =0;
    private int id;
    private String startPoint;
    private String endPoint;
    private boolean isNeedBabySeat;
    private boolean isNeedSmokeCar;
    private CarClass needCarClass;
    private volatile Car carReserver;
    private volatile String orderStatus;
    public static String TYPE_OF_ORDER_STATUS_NEW_ORDER = "NewOrder";
    public static String TYPE_OF_ORDER_STATUS_NEW_ORDER_WITH_RUN_THREAD = "NewOrderWithRunThread";
    public static String TYPE_OF_ORDER_STATUS_WAIT_ORDER = "WaitOrder";
    public static String TYPE_OF_ORDER_STATUS_IN_WORK_ORDER = "InWorkOrder";
    public static String TYPE_OF_ORDER_STATUS_CLOSET_ORDER = "ClosetOrder";
    public static String TYPE_OF_ORDER_STATUS_CANSEL_ORDER = "CanselOrder";
    private TextResourceInstance textResource = TextResourceInstance.getInstance();

    public Order(String startPoint, String endPoint, boolean isNeedBabySeat, boolean isNeedSmokeCar, CarClass needCarClass) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.isNeedBabySeat = isNeedBabySeat;
        this.isNeedSmokeCar = isNeedSmokeCar;
        this.needCarClass = needCarClass;
        this.carReserver = null;
        this.orderStatus = Order.TYPE_OF_ORDER_STATUS_NEW_ORDER;
        this.id = Order.getIdCounter();
    }

    public boolean isNeedBabySeat() {
        return isNeedBabySeat;
    }

    public boolean isNeedSmokeCar() {
        return isNeedSmokeCar;
    }

    public CarClass getNeedCarClass() {
        return needCarClass;
    }

    public synchronized Car getCarReserver() {
        return carReserver;
    }

    public synchronized void setCarReserver(Car carReserver) {
        this.carReserver = carReserver;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public static int getIdCounter() {
        return idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        String orderedCar = (this.carReserver == null) ? ", "+textResource.getValue("prop.key17") : ", " + this.carReserver.toString();
        String orderedType;
        if (this.needCarClass == CarClass.BUSYNESS) orderedType = textResource.getValue("prop.key18");
        else if (this.needCarClass == CarClass.ECONOMIC) orderedType = textResource.getValue("prop.key19");
        else orderedType = textResource.getValue("prop.key20");
        String orderStatus;
        if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_NEW_ORDER) orderStatus = textResource.getValue("prop.key21");
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_WAIT_ORDER) orderStatus = textResource.getValue("prop.key22");
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_IN_WORK_ORDER) orderStatus = textResource.getValue("prop.key23");
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_CLOSET_ORDER) orderStatus = textResource.getValue("prop.key24");
        else if (this.orderStatus == Order.TYPE_OF_ORDER_STATUS_CANSEL_ORDER) orderStatus = textResource.getValue("prop.key25");
        else orderStatus = textResource.getValue("prop.key26");
        return textResource.getValue("prop.key27")+": "+orderStatus+". "+textResource.getValue("prop.key28")+": " + this.startPoint + ", "+textResource.getValue("prop.key29")+": " + this.endPoint + ", "+ textResource.getValue("prop.key30") +": " + this.isNeedBabySeat + ", "+textResource.getValue("prop.key31")+": " + this.isNeedSmokeCar + ", "+textResource.getValue("prop.key32")+": " + orderedType + orderedCar;
    }
}
