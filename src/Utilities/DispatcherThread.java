package Utilities;

import Entity.Car;
import Entity.Order;

import java.util.List;

/**
 * Created by admin on 02.11.2016.
 */
public class DispatcherThread implements Runnable {
    private List<Order> orderArrayList = DB.getInstance().getOrdersArrayList();

    public DispatcherThread() {
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

    private void SearchWaitOrder() { // цикл поиска заявок в статусе "Новые", без запущеного потока
        for (int i=0; i < orderArrayList.size();i++) {
            Order order = orderArrayList.get(i);
            if (Order.TYPE_OF_ORDER_STATUS_NEW_ORDER.equals(order.getOrderStatus())) { //если заявка имеет статус "Новая" запускеам поиск машины для заявки в отдельном потоке и помечаем заявку как уже работающую в отдельном потоке
                order.setOrderStatus(Order.TYPE_OF_ORDER_STATUS_NEW_ORDER_WITH_RUN_THREAD);
                new OrderBookingThread(order);
            }
        }
    }

}
