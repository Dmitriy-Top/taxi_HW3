package Utilities;

import Entity.Car;
import Entity.Enums.CarStatus;
import Entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 02.11.2016.
 */
public class CarWorkEmulatorProcess implements Runnable {
    private List<Order> orderArrayList = DB.getInstance().getOrdersArrayList();
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();

    @Override
    public void run() {
        while (true) {
                for (Order order : orderArrayList) {
                    if (Order.TYPE_OF_ORDER_STATUS_IN_WORK_ORDER.equals(order.getOrderStatus())) {//ищем заявки в статусе "В работе"
                        try {
                            Thread.sleep((long) (Math.random() * 10000)); //даем им "поработать" случайно время
                        } catch (InterruptedException e) {
                            massageArrayList.add(e.getMessage());
                        }
                        order.setOrderStatus(Order.TYPE_OF_ORDER_STATUS_CLOSET_ORDER); //Закрываем заявку через смену статуса заявки
                        order.getCarReserver().setCarStatus(CarStatus.FREE); // Освобождаем машину
                        massageArrayList.add("Автомобиль: " + order.getCarReserver().toString() + " ожидает заказа");
                    }
                }
        }
    }

    public CarWorkEmulatorProcess() {
        new Thread(this).start();
    }
}
