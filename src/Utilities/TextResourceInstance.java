package Utilities;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by admin on 25.11.2016.
 */
public class TextResourceInstance {
    private static TextResourceInstance instance;
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle bundle = ResourceBundle.getBundle("resources.prop", locale);

    private TextResourceInstance() {
    }

    public static TextResourceInstance getInstance(){
        if (instance == null) {
            instance= new TextResourceInstance();
        }
        return instance;
    }
    public void setLocale(Locale local){
        this.locale = local;
    }
    public String getValue(String key){
        bundle = ResourceBundle.getBundle("resources.prop", locale);
        String result = bundle.getString(key);
        return result;
    }
}
