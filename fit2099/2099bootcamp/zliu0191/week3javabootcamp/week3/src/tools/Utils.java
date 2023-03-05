package tools;

import sun.font.TrueTypeFont;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <h1>Utils</h1>
 * contain tools that can be used globally
 * @author zirui
 * @version 1.0
 * @see
 */
public class Utils {
    /**generate random id
     *
     * @param low lower bound of random number
     * @param high higher bound of random number
     * @return random number between higher and lower bound
     * @exception
     * @throws
     */
    public static int nextID(int low, int high) {
        Random r = new Random();
        return (r.nextInt(high - low) + low);
    }
    /**
     *
     * @param string lower bound of random number
     * @return random number between higher and lower bound
     * @exception
     * @throws
     */

    public static boolean validDate(String string) throws Exception {
        if (string.length()!=8){
            throw new IllegalArgumentException("Invalid date");
        }
        else {
            return true;
        }
    }
}