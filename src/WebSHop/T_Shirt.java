package WebSHop;

public class T_Shirt extends Item {
    public T_Shirt(int itemID, String itemDescription, float itemInitPrice, int quantity) {
        super(itemID, itemDescription, itemInitPrice, quantity);
    }

    @Override
    public void changeItemDescription(String description) {
        setItemDescription(description);
    }
}
