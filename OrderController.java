package com.ecommerce;

// The client. It only knows about CheckoutFacade, nothing else.
public class OrderController {

    public static void main(String[] args) {
        CheckoutFacade checkout = new CheckoutFacade();

        // 1. normal order
        report(checkout.placeOrder("Laptop-X1", "user_404", "Almaty, Abay ave 10", 1200.00));

        // 2. product is out of stock
        report(checkout.placeOrder("Monitor-27", "user_404", "Almaty, Abay ave 10", 300.00));

        // 3. customer doesn't have enough money
        report(checkout.placeOrder("Mouse-M2", "user_777", "Kaskelen, SDU dorm", 80.00));
    }

    private static void report(boolean ok) {
        System.out.println(ok ? "Client: order placed\n" : "Client: order failed\n");
    }
}
