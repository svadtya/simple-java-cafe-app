/**
 * A class to create ItemCheckout object.
 * ItemCheckout object is used to hold detail information of
 * checkout Item in receipt.
 * @author Siva Aditya
 */

public class ItemCheckout {

    private Item item;
    private int qty;
    private double subtotal; // Price * QTY

    public ItemCheckout(Item item, int qty) {
        setItem(item);
        setQty(qty);
        setSubtotal(qty*item.getPrice());
    }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public void calcSubtotal() { subtotal = qty * item.getPrice(); }
}
