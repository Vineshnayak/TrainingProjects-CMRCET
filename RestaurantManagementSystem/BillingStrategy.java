package RestaurantManagementSystem;

// BillingStrategy interface
public interface BillingStrategy {
    double calculatePrice(MenuItem item);
}

// StandardBillingStrategy class
class StandardBillingStrategy implements BillingStrategy {
    @Override
    public double calculatePrice(MenuItem item) {
        return item.getPrice();
    }
}

// DiscountBillingStrategy class
class DiscountBillingStrategy implements BillingStrategy {
    private double discount;

    public DiscountBillingStrategy(double discount) {
        this.discount = discount > 0 ? discount : 0; // Ensure discount is non-negative
    }

    @Override
    public double calculatePrice(MenuItem item) {
        double discountedPrice = item.getPrice() - discount;
        return Math.max(discountedPrice, 0); // Ensure price doesn't go below zero
    }
}
