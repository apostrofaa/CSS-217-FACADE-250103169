package com.ecommerce;

/**
 * Facade. The client calls one method - placeOrder() - and doesn't need to know
 * that four different services are involved or in what order to call them.
 */
public class CheckoutFacade {

    private final InventoryService inventory = new InventoryService();
    private final PaymentGateway payment = new PaymentGateway();
    private final ShippingService shipping = new ShippingService();
    private final NotificationService notification = new NotificationService();

    public boolean placeOrder(String productId, String customerId, String address, double amount) {
        System.out.println("--- checkout: " + productId + " for " + customerId + " ---");

        if (address == null || address.isBlank()) {
            System.out.println("Order rejected: no delivery address");
            return false;
        }

        if (!inventory.checkStock(productId)) {
            System.out.println("Order rejected: " + productId + " is out of stock");
            return false;
        }
        inventory.reserve(productId);

        if (!payment.charge(customerId, amount)) {
            // money didn't go through, so give the item back to the warehouse
            inventory.release(productId);
            System.out.println("Order rejected: payment failed");
            return false;
        }

        String tracking = shipping.scheduleDelivery(productId, address);
        notification.sendReceipt(customerId, amount, tracking);

        System.out.println("Order done!");
        return true;
    }
}
