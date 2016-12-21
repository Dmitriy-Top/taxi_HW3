package Entity.Enums;

/**
 * Created by admin on 21.12.2016.
 */
public enum CarClass {
    ECONOMIC,
    BUSYNESS;


    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static boolean isHaveVolume(String volume){
      boolean result = false;
      CarClass[] array = CarClass.values();
        for (CarClass carClass: array){
            if (carClass.toString().equals(volume)) result = true;
        }
        return result;
    };

}
