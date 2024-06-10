package WebSHop;

public class Sneakers extends Item {
    public Sneakers(int itemID, String itemDescription, float itemInitPrice, int quantity) {
        super(itemID, itemDescription, itemInitPrice, quantity);
    }

    @Override
    public void changeItemDescription(String description) {
        setItemDescription(description);
    }
}
