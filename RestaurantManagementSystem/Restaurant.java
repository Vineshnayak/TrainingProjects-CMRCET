package RestaurantManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<MenuItem> menu;

    public Restaurant() {
        menu = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMenuItem(MenuItem item) {
        if (item != null) {
            menu.add(item);
        } else {
            throw new IllegalArgumentException("Cannot add a null item to the menu.");
        }
    }

    public boolean removeMenuItem(String name) {
        MenuItem itemToRemove = findMenuItemByName(name);
        if (itemToRemove != null) {
            menu.remove(itemToRemove);
            return true;
        }
        return false;
    }

    public MenuItem findMenuItemByName(String name) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean isMenuEmpty() {
        return menu.isEmpty();
    }

    public void printMenu() {
        if (isMenuEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }
        System.out.println("Restaurant Menu:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }
}
