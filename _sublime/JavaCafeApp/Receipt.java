/**
 * A class to create Receipt object
 * @author Siva Aditya
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt {

    private int receiptID;              // ID is unique
    private static int nextID = 0;      // auto-increment ID
    private Cashier cashier;
    private LocalDate date;
    private ArrayList<ItemCheckout> checkoutList;
    private double total;
    private double cashPayment;
    private double cashChange;
    private String status;              /* Represent whether the payment
                                           is already done or not */

    public Receipt(Cashier cashier) {
        setReceiptID(++nextID);
        setCashier(cashier);
        setDate(LocalDate.now());
        checkoutList = new ArrayList<ItemCheckout>();
        total = cashPayment = cashChange = 0.0;
        setStatus("NOT PAID");
    }

    // Getter setter
    public int getReceiptID() { return receiptID; }
    public void setReceiptID(int receiptID) { this.receiptID = receiptID; }

    public Cashier getCashier() { return cashier; }
    public void setCashier(Cashier cashier) { this.cashier = cashier; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getCashPayment() { return cashPayment; }
    public void setCashPayment(double cashPayment) { this.cashPayment = cashPayment; }

    public double getCashChange() { return cashChange; }
    public void setCashChange(double cashChange) { this.cashChange = cashChange; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getNoOfItem() { return checkoutList.size(); }

    public void calcTotal() {
        total = 0;
        for (ItemCheckout item: checkoutList) {
            total += item.getSubtotal();
        }
    }

    /**
     * This method is used to add Item object to checkoutList
     */
    public void checkoutItem(Item item, int qty) {

        // Check if the same item has been added before
        for (ItemCheckout i: checkoutList) {

            /* If the same item has been added,
             * the qty of the item will be updated and
             * the subtotal for the item will be re-calculated
             */
            if (i.getItem().equals(item)) {
                i.setQty(i.getQty()+qty);
                i.calcSubtotal();
                return;
            }
        }

        // Add the item to checkoutList
        checkoutList.add(new ItemCheckout(item, qty));
    }

    public ItemCheckout removeItem(int index) {
        if (index>checkoutList.size() || index<0)
            return null;

        return checkoutList.remove(index);
    }

    public String inputItemList() {
        String list = "";
        int number = 1;
        for (ItemCheckout i: checkoutList) {
            list += String.format("%s. %s @%s x %s = IDR %s%n",
                    number,
                    i.getItem().getName(),
                    (int) i.getItem().getPrice(),
                    i.getQty(),
                    (int) i.getSubtotal());
            number++;
        }
        return list;
    }

    public String checkoutItemList() {
        calcTotal();
        String list = "";

        list += String.format("%-17s%8s%9s%12s%n%s%n",
                "Item", "Qty", "Price", "Sub Total",
                "-".repeat(48));

        for (ItemCheckout i: checkoutList) {
            list += String.format("%-17s%5s%10s%11s%n",
                    StringHelper.limit(i.getItem().getName(),16),
                    i.getQty(),
                    (int) i.getItem().getPrice(),
                    (int) i.getSubtotal());
        }

        list += "-".repeat(48)+"\n";
        list += String.format("%-15s%30s%n", "Total", (int) total);

        return list;
    }

    /**
     * A method to process and validate the payment of the checkout item
     */
    public boolean processPayment(double payment) {
        calcTotal();
        if (payment - total >= 0) {
            setCashPayment(payment);
            setCashChange(payment-total);
            setStatus("PAID");  // Change receipt status
            return true;
        }
        return false;
    }

    public String printReceipt() {
        if (!status.equals("PAID"))
            return ""; // Can only print receipt that has been paid

        // HEADER
        String note = "JAVA CAFE\n"+"=".repeat(12)+"\n";
        note += "Cashier Name: "+
                StringHelper.limit(cashier.getFullName().toUpperCase(),16)+
                "\n";
        note += String.format("%s%n%-17s%8s%9s%12s%n%s%n",
                "=".repeat(48),
                "Makanan", "Jml", "Harga", "Sub Total",
                "=".repeat(48));

        // CONTENT
        for (ItemCheckout i: checkoutList) {
            note += String.format("%-17s%5s%10s%11s%n",
                    StringHelper.limit(i.getItem().getName(),16),
                    i.getQty(),
                    (int) i.getItem().getPrice(),
                    (int) i.getSubtotal());
        }

        // PAYMENT DETAIL
        note += "=".repeat(48)+"\n";
        note += String.format("%-15s%30s%n%-15s%30s%n%-15s%30s%n",
                "Total Harga", (int) total,
                "Total Bayar", (int) cashPayment,
                "Kembali", (int) cashChange);

        return note;
    }

    /**
     * A method to save receipt to a txt file
     */
    public String saveToFile(String fileName) {
        FileWriter outStream = null;
        try {
            outStream = new FileWriter(fileName+".txt");
            outStream.write(printReceipt());
            outStream.close();
        } catch (IOException ioe) {
            // Return error message to inform that an exception was caught
            return "Something unexpected happened";
        }

        // Return empty string if saving to file is a success
        return "";
    }

    /**
     * This method is used to reset the value of
     * auto-increment ID generator
     */
    public static void resetID(int value) {
        nextID = value;
    }

}



