package RestaurantManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant myRestaurant = new Restaurant();

        // Input restaurant name
        System.out.print("Enter restaurant name: ");
        myRestaurant.setName(scanner.nextLine());
        System.out.println("Restaurant Name: " + myRestaurant.getName());

        // Add menu items
        System.out.println("\nEnter menu items (type 'done' to finish):");
        while (true) {
            System.out.print("Enter item type (Dish/Drink): ");
            String itemType = scanner.nextLine();
            if (itemType.equalsIgnoreCase("done")) break;

            System.out.print("Enter item name: ");
            String itemName = scanner.nextLine();

            System.out.print("Enter item price: ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Enter item price: ");
                scanner.next(); // clear invalid input
            }
            double itemPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            MenuItem menuItem = createMenuItem(itemType, itemName, itemPrice);
            if (menuItem != null) {
                myRestaurant.addMenuItem(menuItem);
                System.out.println("Menu item added: " + itemName);
            }
        }

        // Print the restaurant's menu
        System.out.println("\nRestaurant Menu:");
        myRestaurant.printMenu();

        // Create a new order
        Order order = new Order();
        System.out.println("\nEnter order items (type 'done' to finish):");

        while (true) {
            System.out.print("Enter item name to order: ");
            String orderItemName = scanner.nextLine();
            if (orderItemName.equalsIgnoreCase("done")) break;

            MenuItem orderedItem = myRestaurant.findMenuItemByName(orderItemName);
            if (orderedItem != null) {
                order.addItem(orderedItem);
                System.out.println("Item added to order: " + orderedItem.getName());
                System.out.println("Current total: $" + order.calculateTotal(new StandardBillingStrategy()));
            } else {
                System.out.println("Item not found in menu.");
            }
        }

        // Print the order
        System.out.println("\nOrder Details:");
        order.printOrder();

        // Calculate the total with standard billing strategy
        double totalStandard = order.calculateTotal(new StandardBillingStrategy());
        System.out.println("\nTotal (Standard Billing): $" + totalStandard);

        // Calculate the total with a discount billing strategy
        System.out.print("Enter discount amount (0 for no discount): ");
        double discountAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        BillingStrategy discountStrategy = new DiscountBillingStrategy(discountAmount);
        double totalDiscount = order.calculateTotal(discountStrategy);
        System.out.println("Total (Discount Billing): $" + totalDiscount);

        scanner.close();
    }

    private static MenuItem createMenuItem(String itemType, String itemName, double itemPrice) {
        switch (itemType.toLowerCase()) {
            case "dish":
                return new Dish(itemName, itemPrice);
            case "drink":
                return new Drink(itemName, itemPrice);
            default:
                System.out.println("Invalid item type. Please enter 'Dish' or 'Drink'.");
                return null;
        }
    }
}
