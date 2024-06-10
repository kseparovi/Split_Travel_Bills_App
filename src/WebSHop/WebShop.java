package WebSHop;



import java.util.ArrayList;



import java.util.ArrayList;
import java.util.Scanner;

public class WebShop {
    private String name;
    private ArrayList<Item> items;
    private ArrayList<Customer> customers;
    private ArrayList<Package> packages;
    private ArrayList<Payment> payments; //why is here error? I have imported Payment class from the same package

    public WebShop(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.packages = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public void addItemInWebShop(Item item) {
        items.add(item);
        System.out.println(item.getClass().getSimpleName() + " successfully added to this web shop offer...");
    }

    public void putItemsInPackage(Customer customer) {
        Package pack = new Package(customer);
        Scanner scanner = new Scanner(System.in);

        for (Item item : items) {
            System.out.println("Choose item: " + item.toString() + " just enter number of pieces...");
            System.out.println("Just in web shop inventory: " + item.getQuantity());
            int quantity = scanner.nextInt();
            pack.putItem(item, quantity);
            item.decreaseItemQuantity(quantity);
        }

        System.out.println(pack.toString());
        finishAndPay(pack);
    }

    public void finishAndPay(Package pack) {
        Payment payment = new Payment();
        payments.add(payment);
        payment.possiblePaymentMethods();

        Scanner scanner = new Scanner(System.in);
        String paymentMethod = scanner.nextLine();
        payment.payPackage();

        System.out.println("Package{packageID=" + pack.getPackageItems() + ", customer=" + pack.getCustomer() + ", totalPrice=" + pack.getTotalPrice() + "}");
        System.out.println("Package will be delivered to: " + pack.getCustomer().getAddress());
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Package with id: " + pack.getPackageItems());
        System.out.println("Send to customer: " + pack.getCustomer());
        System.out.println("------------------------------------------------------------------------------");

        packages.add(pack);

        // Update inventory quantities
        for (int i = 0; i < pack.getPackageItems().size(); i++) {
            Item item = pack.getPackageItems().get(i);
            int quantity = pack.getItemsQuantities().get(i);
            item.decreaseItemQuantity(quantity);
        }

        // Print remaining items in the web shop
        System.out.println("******************************* Web shop items *******************************");
        for (Item item : items) {
            System.out.println(item);
        }
    }
}
