package Utilities.ConsoleCommands;

/**
 * Created by admin on 15.11.2016.
 */
public class Help implements ConsoleExecutable{
    @Override
    public void execut() {
        System.out.println("Доступные в консоли комманды");
        System.out.println("neworder - добавить новый заказ. Пример: neworder Ленина 9|Мира 13|true|false|0");
        System.out.println("cancelorder - отменить заказ. Пример: cancelorder 3");
        System.out.println("orderlist - список заказов. Пример: orderlist");
        System.out.println("help - список комманд");
    }
}
