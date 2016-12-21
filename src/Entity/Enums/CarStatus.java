package Entity.Enums;

/**
 * Created by admin on 21.12.2016.
 */
public enum CarStatus {
    FREE,
    RESERVED;


    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
