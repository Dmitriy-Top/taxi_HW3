import Utilities.*;

/**
 * Created by дима on 30.10.2016.
 */
public class main {
    public static void main(String[] args) {
        Initialization.dbInit(); //Инициализация DB
        new DispatcherThread(); //Старт процесса поиска машин
        new UserMassageHelperThread(); //Старт процесса вывода сообщений
        new OrderReaderThread(); //Старт консоли пользователя
    }
}
