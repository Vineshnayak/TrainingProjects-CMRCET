package RestaurantManagementSystem;

public class Dish extends MenuItem {
    public Dish(String name, double price) {
        super(name, validatePrice(price));
    }

    private static double validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        return price;
    }

    @Override
    public String toString() {
        return "Dish: " + getName() + ", Price: $" + getPrice();
    }
}

// Drink class extending MenuItem
class Drink extends MenuItem {
    public Drink(String name, double price) {
        super(name, validatePrice(price));
    }

    private static double validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        return price;
    }

    @Override
    public String toString() {
        return "Drink: " + getName() + ", Price: $" + getPrice();
    }
}
