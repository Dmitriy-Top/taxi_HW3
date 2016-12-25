package Utilities;

import Utilities.ConsoleCommands.CommandsFactory;
import Utilities.ConsoleCommands.ConsoleExecutable;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by admin on 15.11.2016.
 */
public class OrderReaderThread implements Runnable {
    private Scanner sc = Reader.getReader();
    private TextResourceInstance textResource = TextResourceInstance.getInstance();

    public OrderReaderThread() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(textResource.getValue("prop.key1"));
        System.out.println(textResource.getValue("prop.key2"));
        while(true){
            String input = sc.nextLine();
            ConsoleExecutable command = CommandsFactory.getCommand(input);
            command.execut();
        }
    }
}
