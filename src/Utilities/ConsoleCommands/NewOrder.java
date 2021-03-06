package Utilities.ConsoleCommands;

import Entity.Car;
import Entity.Enums.CarClass;
import Entity.Order;
import Utilities.DB;
import Utilities.TextResourceInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by admin on 15.11.2016.
 */
public class NewOrder implements ConsoleExecutable {
    private List<Order> orderArrayList = DB.getInstance().getOrdersArrayList();
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();
    private TextResourceInstance textResource = TextResourceInstance.getInstance();
    String params;

    public NewOrder(String params) {
        this.params = params;
    }

    @Override
    public void execut() {
        Order order = orderFactory(params);
        if (order != null) {
            orderArrayList.add(order);
            massageArrayList.add(textResource.getValue("prop.key7")+" ID:"+order.getId());
        } else {
            massageArrayList.add(textResource.getValue("prop.key8"));
        }

    }

    private Order orderFactory(String params) {
        Order order = null;
        String orderFields[] = params.split("\\|");
        if (checkOrderFieldsIsCorrect(orderFields)) {
            order = new Order(orderFields[0], orderFields[1], Boolean.parseBoolean(orderFields[2]), Boolean.parseBoolean(orderFields[3].toUpperCase()), CarClass.valueOf(orderFields[4].toUpperCase()));
        }
        return order;
    }

    private boolean checkOrderFieldsIsCorrect(String[] orderFields) {
        boolean isCorrect = true;
        if (orderFields.length < 5) isCorrect = false;
        try {
            if (!checkRegExpAdress(orderFields[0])) isCorrect = false;
            if (!checkRegExpAdress(orderFields[1])) isCorrect = false;
            if (!"true".equals(orderFields[2]) && !"false".equals(orderFields[2])) isCorrect = false;
            if (!"true".equals(orderFields[3]) && !"false".equals(orderFields[3])) isCorrect = false;
            if (!CarClass.isHaveVolume(orderFields[4]))
                isCorrect = false;
        } catch (NumberFormatException e) {
            isCorrect = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            isCorrect = false;
        }
        return isCorrect;
    }
    private boolean checkRegExpAdress(String adress){
        String pattern = "^(ул\\.|улица|бульвар|б-р|проспект|пр\\.) (.*) (\\d+)$";
        Pattern pattern1 = Pattern.compile(pattern);
        return pattern1.matcher(adress).matches();
    }
}
