package Utilities.ConsoleCommands;

/**
 * Created by admin on 15.11.2016.
 */
public class CommandsFactory {
    public static ConsoleExecutable getCommand(String input) {
        String[] command = input.split("\\s+");
        String args ="";
        for (int i = 1; i< command.length;i++){
            args += command[i];
            if (i!=command.length-1) args+=" ";
        }

        switch (command[0]) {
            case "neworder":
                return new NewOrder(args);
            case "cancelorder":
                return new CancelOrder(args);
            case "orderlist":
                return new OrderList();
            case "language":
                return new Language(args);
            default:
                return new Help();
        }
    }
}
