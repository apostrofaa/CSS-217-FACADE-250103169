package com.ecommerce;

// Emails / SMS to the customer.
public class NotificationService {

    public void sendReceipt(String customerId, double amount, String trackingNumber) {
        System.out.println("[Notification] receipt sent to " + customerId
                + ": paid $" + amount + ", tracking " + trackingNumber);
    }
}
