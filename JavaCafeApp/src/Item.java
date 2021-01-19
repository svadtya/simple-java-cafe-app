/**
 * A class to create Item object
 * @author Siva Aditya
 */

import java.util.Objects;

public class Item {

    private int itemID;                 // ID is unique
    private static int nextID = 0;      // auto-increment ID
    private String name;
    private double price;

    // Default constructor
    public Item() { this("unknown",0.0); }

    // Constructor with parameters
    public Item(String name, double price) {
        setItemID(++nextID);
        setName(name);
        setPrice(price);
    }

    // Getter setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getItemID() { return itemID; }
    public void setItemID(int itemID) { this.itemID = itemID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemID == item.itemID; // Item ID is unique
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID);
    }

    @Override
    public String toString() {
        return String.format("[%s] -- %s (IDR %.2f)",
                itemID,
                name,
                price);
    }
}
