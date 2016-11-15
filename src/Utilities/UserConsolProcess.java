package Utilities;

import Utilities.ConsoleCommands.CommandsFactory;
import Utilities.ConsoleCommands.ConsoleExecutable;
import java.util.Scanner;

/**
 * Created by admin on 15.11.2016.
 */
public class UserConsolProcess implements Runnable {
    private Scanner sc = Reader.getReader();

    public UserConsolProcess() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Добро пожаловать в автоматический диспетчер службы такси");
        System.out.println("help - список доступных команд");
        while(true){
            String input = sc.nextLine();
            ConsoleExecutable command = CommandsFactory.getCommand(input);
            command.execut();
        }
    }
}
