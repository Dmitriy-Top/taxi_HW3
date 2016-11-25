package Utilities.ConsoleCommands;

import Entity.Order;
import Utilities.DB;
import Utilities.TextResourceInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15.11.2016.
 */
public class CancelOrder implements ConsoleExecutable {
    private List<Order> orderArrayList = DB.getInstance().getOrdersArrayList();
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();
    private TextResourceInstance textResource = TextResourceInstance.getInstance();
    String params;
    int id;

    @Override
    public void execut() {
        if (isParsable(params)){
            id = Integer.parseInt(params);
            orderArrayList.get(id).setOrderStatus(Order.TYPE_OF_ORDER_STATUS_CANSEL_ORDER);
            massageArrayList.add(textResource.getValue("prop.key11")+" "+id+" "+textResource.getValue("prop.key12"));
        }
        else{
            massageArrayList.add(textResource.getValue("prop.key13"));
        }

    }

    public CancelOrder(String params) {
        this.params = params;
    }

    private static boolean isParsable(String input) {
        boolean parsable = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }
}
