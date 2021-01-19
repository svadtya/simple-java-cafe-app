/**
 * A comparator class to sort Cashier object
 * @author Siva Aditya
 */

import java.util.Comparator;

public class SortCashierName implements Comparator<Cashier> {
    @Override
    public int compare(Cashier cs1, Cashier cs2) {
        return cs1.getFullName().compareTo(cs2.getFullName());
    }
}
