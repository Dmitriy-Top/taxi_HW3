package Utilities.ConsoleCommands;

import Entity.Order;
import Utilities.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15.11.2016.
 */
public class OrderList implements ConsoleExecutable {
    private List<Order> orderArrayList = DB.getInstance().getOrdersArrayList();
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();

    @Override
    public void execut() {
        String result = "";
        if (orderArrayList.size() > 0) {
            for (int i = 0; i < orderArrayList.size(); i++) {
                result += "ID "+i+" - "+orderArrayList.get(i).toString()+"\n";
            }
        } else {
            result = "Нет заявок";
        }
        massageArrayList.add(result);
    }
}
