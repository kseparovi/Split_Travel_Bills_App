package WebSHop;

public abstract class Item {
    private int itemID;
    private String itemDescription;
    private float itemInitPrice;
    private int quantity;
    private float itemCurrentPrice;

    public Item(int itemID, String itemDescription, float itemInitPrice, int quantity) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.itemInitPrice = itemInitPrice;
        this.quantity = quantity;
        this.itemCurrentPrice = itemInitPrice;
    }

    public void decreaseItemQuantity(int n) {
        if (quantity >= n) {
            quantity -= n;
        }
    }

    public abstract void changeItemDescription(String description);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{itemID=" + itemID +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemInitPrice=" + itemInitPrice +
                ", quantity=" + quantity +
                ", currentPrice=" + itemCurrentPrice + '}';
    }

    // Getters and setters
    public int getItemID() {
        return itemID;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public float getItemInitPrice() {
        return itemInitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getItemCurrentPrice() {
        return itemCurrentPrice;
    }

    public void setItemCurrentPrice(float itemCurrentPrice) {
        this.itemCurrentPrice = itemCurrentPrice;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

}
