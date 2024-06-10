package WebSHop;


public class Jacket extends Item {
    public Jacket(int itemID, String itemDescription, float itemInitPrice, int quantity) {
        super(itemID, itemDescription, itemInitPrice, quantity);
    }

    @Override
    public void changeItemDescription(String description) {
        setItemDescription(description);
    }


}
