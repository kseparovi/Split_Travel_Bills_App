package WebSHop;

import java.util.ArrayList;

public class Package {
    private Customer customer;
    private ArrayList<Integer> itemsQuantities;
    private long packageID;
    private ArrayList<Item> packageItems;
    private float totalPrice;

    public Package(Customer customer) {
        this.customer = customer;
        this.itemsQuantities = new ArrayList<>();
        this.packageItems = new ArrayList<>();
        this.packageID = System.currentTimeMillis();
        this.totalPrice = 0;
    }

    public void putItem(Item item, int quantity) {
        packageItems.add(item);
        itemsQuantities.add(quantity);
        totalPrice += item.getItemInitPrice() * quantity;
        System.out.println("Added item in package: " + item.toString() + " into the package - " + quantity + " units!");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================== Package - ").append(packageID).append(" ==========================\n");
        for (int i = 0; i < packageItems.size(); i++) {
            Item item = packageItems.get(i);
            int quantity = itemsQuantities.get(i);
            sb.append(item.toString()).append("\nNum of units: ").append(quantity).append("\nPrice for this items: ").append(item.getItemInitPrice() * quantity).append("\n");
        }
        sb.append("Total price: ").append(totalPrice).append("\n");
        return sb.toString();
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public ArrayList<Item> getPackageItems() {
        return packageItems;
    }

    public ArrayList<Integer> getItemsQuantities() {
        return itemsQuantities;
    }

    public long getPackageID() {
        return packageID;
    }
}
