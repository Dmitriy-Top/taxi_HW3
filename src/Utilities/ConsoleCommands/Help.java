package Utilities.ConsoleCommands;

import Utilities.DB;
import Utilities.TextResourceInstance;

import java.util.List;

/**
 * Created by admin on 15.11.2016.
 */
public class Help implements ConsoleExecutable{
    @Override
    public void execut() {
        TextResourceInstance textResource = TextResourceInstance.getInstance();
        List<String> massageArrayList = DB.getInstance().getMassageArrayList();
        massageArrayList.add(textResource.getValue("prop.key3"));
    }
}
