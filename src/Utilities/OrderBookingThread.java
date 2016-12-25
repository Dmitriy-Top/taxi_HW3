package Utilities;

import Entity.Car;
import Entity.Enums.CarStatus;
import Entity.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by дима on 25.12.2016.
 */
public class OrderBookingThread implements Runnable {
    private List<Car> carArrayList = DB.getInstance().getCarsArrayList();
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();
    private TextResourceInstance textResource = TextResourceInstance.getInstance();
    Order currentOrder;
    boolean isAliveThread = true;
    Date startDate = new Date();

    @Override
    public void run() {
        while (isAliveThread) {
            if (startDate.getTime() + 60000L <= new Date().getTime()) { //проверка на срок жизни потока в 60 секунд
                massageArrayList.add(textResource.getValue("prop.key33")+", ID:"+currentOrder.getId()); //если поток "просрочен", говорим пользователю о том, что машина не найдена
                isAliveThread = false;
            }
            searchFreeCarForOrder(currentOrder);
            if (currentOrder.getOrderStatus().equals(Order.TYPE_OF_ORDER_STATUS_NEW_ORDER_WITH_RUN_THREAD)) { //если для новой заявки не найден автомобиль(статус заявки не поменялся после первого прохода), меняем ее статус на в "В ожидании" и предупреждаем пользователя об ожидании.
                currentOrder.setOrderStatus(Order.TYPE_OF_ORDER_STATUS_WAIT_ORDER);
                massageArrayList.add(textResource.getValue("prop.key5")+", ID:"+currentOrder.getId());
            }
            if (currentOrder.getOrderStatus().equals(Order.TYPE_OF_ORDER_STATUS_IN_WORK_ORDER)) new ExecEmulationThread(currentOrder); //если заявка перешла в статус "в работе", отдаем ее под управление эмулятора выполнения заказа
        }
    }

    public OrderBookingThread(Order currentOrder) {
        this.currentOrder = currentOrder;
        new Thread(this).start();
    }


    private void searchFreeCarForOrder(Order order) { // цикл поиска и назначения автомобиля для заявки
        for (Car car : carArrayList) {
            synchronized (car) {
                if (compareOrderToCar(car, order)) {
                    car.setCarStatus(CarStatus.RESERVED);
                    order.setOrderStatus(Order.TYPE_OF_ORDER_STATUS_IN_WORK_ORDER);// статус заказ меняем на "В работе"
                    order.setCarReserver(car);// закрепляем машину за заказом
                    isAliveThread = false; //останавливаем поток по поиску машин
                    massageArrayList.add(textResource.getValue("prop.key6") + " " + car.toString() + ", ID:" + order.getId()); // сообщаем пользователю
                    break;
                }
            }
        }


    }

    private static boolean compareOrderToCar(Car car, Order order) { //сопоставление машины и заказа
        boolean isSuitCar = true;
        if (CarStatus.RESERVED.toString().equals(car.getCarStatus().toString())) isSuitCar = false;
        if (!(car.isHaveBabySeat() == order.isNeedBabySeat())) isSuitCar = false;
        if (!(car.isSmokeCar() == order.isNeedSmokeCar())) isSuitCar = false;
        if (!(car.getCarClass() == order.getNeedCarClass())) isSuitCar = false;
        return isSuitCar;
    }
}
