/**
 * A container class for Cashier, Item, and Receipt object
 * @author Siva Aditya
 */

import java.util.ArrayList;
import java.util.Collections;
import java.time.format.DateTimeFormatter;
//import java.util.Comparator;

public class JavaCafe {

    private ArrayList<Cashier> cashierList;
    private ArrayList<Item> itemList;
    private ArrayList<Receipt> receiptList;

    public JavaCafe() {
        //Some cashier users are hard-coded for testing purposes
        cashierList = new ArrayList<Cashier>() {
            {
                add(new Cashier());
                add(new Cashier("haryinternship","12341234","Hary"));
                add(new Cashier("sivaaditya","delapan8",
                        "I Made Siva Aditya Surya"));
            }
        };
        //Some items are hard-coded for testing purposes
        itemList = new ArrayList<Item>() {
            {
                add(new Item("Nasi Goreng", 15000));
                add(new Item("Kwetiau Seafood", 25000));
                add(new Item("Bihun Ayam", 20000));
                add(new Item("Sayur Asem", 18000));
                add(new Item("Ayam Bakar", 50000));
                add(new Item("Teh Manis", 5000));
            }
        };
        receiptList = new ArrayList<Receipt>();
    }

    //-------Cashier Collection related method-------//

    public int getNoOfUser() {
        return cashierList.size();
    }

    public void registUser(String user, String pass, String name) {
        cashierList.add(new Cashier(user,pass,name));
    }

    public Cashier loginUser(String username, String password) {
        for (Cashier cashier: cashierList) {
            if (cashier.getUsername().equals(username) &&
                cashier.getPassword().equals(password))
                return cashier;
        }
        return null;
    }

    public boolean findUser(String username) {
        for (Cashier cashier: cashierList) {
            if (cashier.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public Cashier deleteUser(int index) {
        // FATAL EXCEPTION ERROR -->  >=
        // IndexOutOfBoundException
        if (index>=cashierList.size() || index<0)
            return null;

        return cashierList.remove(index);
    }

    public String allUser() {
        // Sort cashier name alphabetically

        // ANONYMOUS INNER CLASS
//        Collections.sort(cashierList,new Comparator<Cashier>() {
//            @Override
//            public int compare(Cashier cs1, Cashier cs2) {
//                return cs1.getFullName().compareTo(cs2.getFullName());
//            }
//        });

        // LAMBDA EXPRESSION
        Collections.sort(cashierList,
                (cs1,cs2) -> cs1.getFullName().compareTo(cs2.getFullName()));

        // Get data in string + formatting
        String list = "";
        int number = 1;
        list += String.format("%s%n%-5s%20s%20s%n%s%n",
                "-".repeat(52),
                " NO", "CASHIER NAME", "USERNAME",
                "-".repeat(52));
        for (Cashier cashier: cashierList) {
            list += String.format("%-5s%-30s%-20s%n",
                    " "+number,
                    StringHelper.limit(cashier.getFullName(),22),
                    cashier.getUsername());
            number++;
        }

        // Add closing line
        list += "-".repeat(52)+"\n";

        return list;
    }

    //--------Item Collection related method--------//

    public int getNoOfItem() {
        return itemList.size();
    }

    public void addItem(String name, double price) {
        itemList.add(new Item(name,price));
    }

    public boolean findItem(String itemName) {
        for (Item item: itemList) {
            if (item.getName().toUpperCase().equals(itemName.toUpperCase()))
                return true;
        }
        return false;
    }

    public Item getItem(int itemID) {
        for (Item item: itemList) {
            if (item.getItemID() == itemID)
                return item;
        }
        return null;
    }

    public Item getItem(String itemName) {
        for (Item item: itemList) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public Item deleteItem(int index) {
        // FATAL EXCEPTION ERROR -->  >=
        // IndexOutOfBoundException
        if (index>itemList.size() || index<0)
            return null;

        return itemList.remove(index);
    }

    public String allItem() {
        // Sort item by name, alphabetically

        // ANONYMOUS INNER CLASS
//        Collections.sort(itemList, new Comparator<Item>() {
//            @Override
//            public int compare(Item it1, Item it2) {
//                return it1.getName().compareTo(it2.getName());
//            }
//        });

        // LAMBDA EXPRESSION
        Collections.sort(itemList,
                (it1,it2) -> it1.getName().compareTo(it2.getName()));

        // Record data in a string variable + formatting
        String allItem = String.format("%-15s%8s%25s%n",
                "  ID", "ITEM", "PRICE");
        allItem += "=".repeat(50);
        allItem += "\n";
        for (Item item: itemList) {
            allItem += String.format("%-15s%-15s%14s%n%s%n",
                    "  "+item.getItemID(),
                    StringHelper.limit(item.getName(),16),
                    (int) item.getPrice(),
                    "-".repeat(50));
        }
        return allItem;
    }

    //-------Receipt Collection related method-------//

    public void recordReceipt(Receipt receipt) {
        receiptList.add(receipt);
    }

    public int getNoOfReceipt() {
        return receiptList.size();
    }

    /**
     * This method is used to display all receipt recorded in collection
     */
    public String viewReceipt() {
        String list = "";
        int number = 1;         // Make the list to be numbered
        double totalCash = 0;   // Calculate grand total
        for (Receipt receipt: receiptList) {
            list += String.format("%s. Receipt ID [%s]; %s; "+
                    "Cashier: %s; Total: IDR %s%n",
                    number,
                    receipt.getReceiptID(),
                    receipt.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    receipt.getCashier().getUsername(),
                    (int) receipt.getTotal());
            totalCash += receipt.getTotal();
            number++;
        }

        // Display grand total of all receipt
        if (!list.equals("")) {
            list += String.format("%nTotal Cash Received : IDR %s%n",
                    (int) totalCash);
        }

        return list;
    }

    /**
     * This method is used to display all receipt recorded in collection
     * that were processed/inputted by a specific cashier
     */
    public String viewReceipt(Cashier cashier) {
        String list = "";
        int number = 1;
        for (Receipt receipt: receiptList) {
            /* Check whether the receipt is inputted
               by the cashier in parameter */
            if (receipt.getCashier().equals(cashier)) {
                list += String.format("%s. Receipt ID [%s]; %s; "+
                                "Total: IDR %s%n",
                        number,
                        receipt.getReceiptID(),
                        receipt.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        (int) receipt.getTotal());
                number++;
            }
        }

        return list;
    }

    /**
     * This method is used when a cashier cancelled a receipt.
     * The auto-increment ID generator in class Receipt will be reset
     * to prevent jumping ID number of the next receipt.
     */
    public void resetReceiptID() {
        Receipt.resetID(receiptList.size()); /* Reset according to the
                                                number of receipt in
                                                collection */
    }
}
