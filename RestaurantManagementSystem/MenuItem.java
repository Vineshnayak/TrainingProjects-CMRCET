package RestaurantManagementSystem;

// Abstract base class for menu items
public abstract class MenuItem {
    protected String name;
    protected double price;

    public MenuItem(String name, double price) {
        this.name = name;
        setPrice(price); // Use setter for validation
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    protected void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public void display() {
        System.out.printf("%s: $%.2f%n", name, price); // More formatted output
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f", name, price); // More formatted output
    }
}
