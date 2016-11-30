import Entity.*;
import Utilities.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by дима on 30.10.2016.
 */
public class main {
    public static void main(String[] args) {
        Initialization.dbInit(); //Инициализация DB
        // new OrderDispatcherProcess(); //Старт процесса поиска машин
        new CarWorkEmulatorProcess(); //Старт эмулятора выполнения заказов (Работает со сбоями)
        new ShowUserMassageProcess(); //Старт процесса вывода сообщений
        new UserConsolProcess(); //Старт консоли пользователя


    }
}
