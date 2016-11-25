package Utilities.ConsoleCommands;

import Utilities.DB;
import Utilities.TextResourceInstance;

import java.util.List;
import java.util.Locale;

/**
 * Created by admin on 25.11.2016.
 */
public class Language implements ConsoleExecutable {

    private Locale local;

    public Language(String args) {
        this.local = parseLocal(args);
    }

    @Override
    public void execut() {
        TextResourceInstance textResource = TextResourceInstance.getInstance();
        List<String> massageArrayList = DB.getInstance().getMassageArrayList();
        TextResourceInstance.getInstance().setLocale(this.local);
        massageArrayList.add(textResource.getValue("prop.key4"));
    }

    private Locale parseLocal(String localName) {
        switch (localName) {
            case "russian":
                return new Locale("ru", "RU");
            case "english":
                return new Locale("en", "EN");
            default:
                return new Locale("ru", "RU");
        }
    }


}
