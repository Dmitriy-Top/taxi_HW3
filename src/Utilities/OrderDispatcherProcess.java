package Utilities;

import Entity.Car;
import Entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 02.11.2016.
 */
public class OrderDispatcherProcess implements Runnable {
    private List<Car> carArrayList = DB.getInstance().getCarsArrayList();
    private List<Order> orderArrayList = DB.getInstance().getOrdersArrayList();
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();

    public OrderDispatcherProcess() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            if (orderArrayList.size() > 0) {
                SearchWaitOrder();
            }
        }

    }


    private void SearchWaitOrder() { // цикл поиска заявок в статусе "В ожидании" и "Новые"
        synchronized (orderArrayList) {
            for (Order order : orderArrayList) {
                if (Order.TYPE_OF_ORDER_STATUS_NEW_ORDER.equals(order.getOrderStatus()) || Order.TYPE_OF_ORDER_STATUS_WAIT_ORDER.equals(order.getOrderStatus())) { //если заявка имеет статус "Новая" или "В ожидании" запускеам поиск машины для заявки
                    searchFreeCarForOrder(order);
                }
                if (order.getOrderStatus().equals(Order.TYPE_OF_ORDER_STATUS_NEW_ORDER)) { //если для новой заявки не найден автомобиль(статус заявки не поменялся), меняем ее статус на в "В ожидании" и предупреждаем пользователя об ожидании.
                    order.setOrderStatus(Order.TYPE_OF_ORDER_STATUS_WAIT_ORDER);
                    massageArrayList.add("В данный момент нет подходящего для вас автомобиля, заявка ожидает подходящего, освободившегося автомобиля");
                }
            }
        }
    }

    private void searchFreeCarForOrder(Order order) { // цикл поиска и назначения автомобиля для заявки
        for (Car car : carArrayList) {
            if (compareOrderToCar(car, order)) {
                order.setCarReserver(car);// закрепляем машину за заказом
                order.setOrderStatus(Order.TYPE_OF_ORDER_STATUS_IN_WORK_ORDER);// статус заказ меняем на "В работе"
                car.setCarStatus(Car.TYPE_OF_STATUS_RESERVED); // статус машины меняем на "Занята"
                massageArrayList.add("На ваш заказ назначенна " + car.toString()); // сообщаем пользователю
                break;
            }
        }


    }

    private static boolean compareOrderToCar(Car car, Order order) { //сопоставление машины и заказа
        boolean isSuitCar = true;
        if (Car.TYPE_OF_STATUS_RESERVED.equals(car.getCarStatus())) return false;
        if (!(car.isHaveBabySeat() == order.isNeedBabySeat())) isSuitCar = false;
        if (!(car.isSmokeCar() == order.isNeedSmokeCar())) isSuitCar = false;
        if (!(car.getCarClass() == order.getNeedCarClass())) isSuitCar = false;
        return isSuitCar;
    }
}
