package WebSHop;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Payment {
    private long paymentID;

    public Payment() {
        this.paymentID = System.currentTimeMillis();
    }

    public void possiblePaymentMethods() {
        System.out.println("Possible payment methods: cash on delivery, credit card, PayPal...");
        System.out.println("Please choose payment method..."
                + "\n1. Cash on delivery"
                + "\n2. Credit card"
                + "\n3. PayPal"
                + "\n4. Cancel");
    }

    public void payPackage() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Payment method: Cash on delivery");
                    break;
                case 2:
                    System.out.println("Payment method: Credit card");
                    break;
                case 3:
                    System.out.println("Payment method: PayPal");
                    break;
                case 4:
                    System.out.println("Payment canceled.");
                    break;
                default:
                    System.out.println("Invalid choice. Payment canceled.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Payment canceled.");
        }

    }

    @Override
    public String toString() {
        return "Payment{paymentID=" + paymentID + '}';
    }
}
