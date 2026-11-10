package com.ecommerce;

import java.util.HashMap;
import java.util.Map;

// Talks to the bank. Here the "bank" is just a map of balances.
public class PaymentGateway {

    private final Map<String, Double> balances = new HashMap<>();

    public PaymentGateway() {
        balances.put("user_404", 2500.0);
        balances.put("user_777", 50.0);
    }

    public boolean charge(String customerId, double amount) {
        double balance = balances.getOrDefault(customerId, 0.0);
        if (amount <= 0 || balance < amount) {
            System.out.println("[Payment] declined for " + customerId + " (needs $" + amount + ", has $" + balance + ")");
            return false;
        }
        balances.put(customerId, balance - amount);
        System.out.println("[Payment] charged $" + amount + " from " + customerId);
        return true;
    }
}
