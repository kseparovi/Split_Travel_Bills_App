package WebSHop;

public class TestWebShop {

    public static void main(String[] args) {
        WebShop ws = new WebShop("WEB_BEST_OFF");
        ws.addItemInWebShop(new Jacket(1, "Jacket", 100, 1));
        ws.addItemInWebShop(new Sneakers(2, "Sneakers", 50, 1));
        ws.addItemInWebShop(new Jacket(3, "Jacket", 100, 1));
        ws.addItemInWebShop(new Sneakers(4, "Sneakers", 50, 1));
        ws.addItemInWebShop(new T_Shirt(5, "T-Shirt", 20, 1));

        Customer customer = new Customer (1, "John", "New York");
        ws.putItemsInPackage(customer);


    }
}
