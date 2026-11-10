package com.ecommerce;

// Sends the package and gives back a tracking number.
public class ShippingService {

    private int nextTracking = 1001;

    public String scheduleDelivery(String productId, String address) {
        String tracking = "TRK-" + nextTracking++;
        System.out.println("[Shipping] " + productId + " -> " + address + ", tracking " + tracking);
        return tracking;
    }
}
