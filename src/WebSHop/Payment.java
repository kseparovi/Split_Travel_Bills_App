package WebSHop;

public class Payment {
    private long paymentID;

    public Payment() {
        this.paymentID = System.currentTimeMillis();
    }

    public void possiblePaymentMethods() {
        System.out.println("You can pay by:\nCOD Cash On Delivery\nCC Credit Card\nPP PayPal service");
    }

    public void payPackage() {
        System.out.println("Payment method - cash on delivery...");
    }

    @Override
    public String toString() {
        return "Payment{paymentID=" + paymentID + '}';
    }
}
