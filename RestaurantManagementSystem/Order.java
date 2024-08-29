package RestaurantManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        if (item != null) {
            items.add(item);
        } else {
            throw new IllegalArgumentException("Cannot add a null item to the order.");
        }
    }

    public void printOrder() {
        if (items.isEmpty()) {
            System.out.println("Order is empty.");
            return;
        }
        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    public double calculateTotal(BillingStrategy strategy) {
        double total = 0;
        for (MenuItem item : items) {
            total += strategy.calculatePrice(item);
        }
        return total;
    }

    public String getOrderSummary(BillingStrategy strategy) {
        StringBuilder summary = new StringBuilder();
        summary.append("Order Summary:\n");
        printOrder();
        double total = calculateTotal(strategy);
        summary.append(String.format("Total: $%.2f\n", total));
        return summary.toString();
    }

    public void clearOrder() {
        items.clear();
    }
}
