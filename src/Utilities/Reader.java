package Utilities;

import java.util.Scanner;

/**
 * Created by admin on 31.10.2016.
 */
public class Reader {
    private static Scanner instance;

    public static synchronized Scanner getReader() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }

    private Reader() {
    }
}
