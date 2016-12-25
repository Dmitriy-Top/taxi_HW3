package Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 02.11.2016.
 */
public class UserMassageHelperThread implements Runnable{
    private List<String> massageArrayList = DB.getInstance().getMassageArrayList();

    public UserMassageHelperThread() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(true){
            if (massageArrayList.size() > 0){ // если что то есть на вывод
                System.out.println(massageArrayList.get(0)); // выводим нужным методом
                massageArrayList.remove(0); // удаляем из очереди выведенное сообщение
            }
        }

    }
}
