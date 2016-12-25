package Utilities;

import Entity.Car;
import Entity.Enums.CarStatus;
import Entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 02.11.2016.
 */
public class ExecEmulationThread implements Runnable {
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();
    Order currentOrder;

    @Override
    public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 10000)); //даем ей "поработать" случайно время
                    //Thread.sleep(20000L); //даем ей "поработать" 20 секунд
                } catch (InterruptedException e) {
                    massageArrayList.add(e.getMessage());
                }
        massageArrayList.add("Заявка на: " + currentOrder.getEndPoint() + " выполнена"+", ID:"+currentOrder.getId()+"\n"+"Автомобиль: " + currentOrder.getCarReserver().toString() + " ожидает заказа");
        currentOrder.setOrderStatus(Order.TYPE_OF_ORDER_STATUS_CLOSET_ORDER); //Закрываем заявку через смену статуса заявки
        currentOrder.getCarReserver().setCarStatus(CarStatus.FREE); // Освобождаем машину
    }

    public ExecEmulationThread(Order order) {
        this.currentOrder = order;
        new Thread(this).start();
    }
}
