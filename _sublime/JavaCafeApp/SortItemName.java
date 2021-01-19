/**
 * A comparator class to sort Cashier object
 * @author Siva Aditya
 */

import java.util.Comparator;

public class SortItemName implements Comparator<Item> {
    @Override
    public int compare(Item it1, Item it2) {
        return it1.getName().compareTo(it2.getName());
    }
}

