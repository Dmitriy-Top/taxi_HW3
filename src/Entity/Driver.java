package Entity;

/**
 * Created by дима on 30.10.2016.
 */
public class Driver {
    private String name;
    private String telephone;

    public Driver(String name, String telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }
}
