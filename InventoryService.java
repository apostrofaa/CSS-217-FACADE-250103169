package com.ecommerce;

import java.util.HashMap;
import java.util.Map;

// Warehouse. Knows how many of each product we have.
public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        // fake warehouse data, normally this would come from a database
        stock.put("Laptop-X1", 3);
        stock.put("Mouse-M2", 10);
        stock.put("Monitor-27", 0);
    }

    public boolean checkStock(String productId) {
        int left = stock.getOrDefault(productId, 0);
        System.out.println("[Inventory] " + productId + ": " + left + " left");
        return left > 0;
    }

    public void reserve(String productId) {
        stock.merge(productId, -1, Integer::sum);
        System.out.println("[Inventory] reserved 1 x " + productId);
    }

    // used when something goes wrong after we already reserved the item
    public void release(String productId) {
        stock.merge(productId, 1, Integer::sum);
        System.out.println("[Inventory] reservation cancelled, " + productId + " is back in stock");
    }
}
