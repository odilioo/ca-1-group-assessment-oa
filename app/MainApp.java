package app;

import ds.QueueStorage;
import ds.StorageInterface;
import model.FoodItem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainApp {
    private final StorageInterface<FoodItem> storage = new QueueStorage<>(8);
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MainApp().run();
    }
    private void run() {
        System.out.println("=== FastFoodFIFO - Menu ===");
        boolean running = true;
        while (running) {
            printMenu();
            String option = scanner.nextLine().trim();
            switch (option) {
                case "1":
                    handleEnqueue();
                    break;
                case "2":
                    handleDequeue();
                    break;
                case "3":
                    handlePeek();
                    break;
                case "4":
                    handleList();
                    break;
                case "5":
                    running = false;
                    break;
                case "6":
                    handleSearch();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        System.out.println("Exiting...");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1) Add item"); /*Enqueue*/
        System.out.println("2) Remove item"); /*Dequeue*/
        System.out.println("3) View next item"); /*Peek*/
        System.out.println("4) List all items");
        System.out.println("5) Exit");
        System.out.println("6) Search item by name");
        System.out.print("Choose: ");
    }

    private void handleEnqueue() {
        try {
            if (storage.isFull()) {
                System.out.println("Storage is full. Remove items before adding new ones.");
                return;
            }
            System.out.print("Item name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Item weight (grams): ");
            double weight = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Production date (YYYY-MM-DD): ");
            LocalDate prod = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Best before date (YYYY-MM-DD): ");
            LocalDate best = LocalDate.parse(scanner.nextLine().trim());
            FoodItem item = new FoodItem(name, weight, prod, best);
            storage.enqueue(item);
            System.out.println("Added: " + item);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleDequeue() {
        try {
            FoodItem removed = storage.dequeue();
            System.out.println("Removed: " + removed);
        } catch (NoSuchElementException e) {
            System.out.println("Storage is empty.");
        }
    }

    private void handlePeek() {
        try {
            FoodItem next = storage.peek();
            System.out.println("Next item: " + next);
        } catch (NoSuchElementException e) {
            System.out.println("Storage is empty.");
        }
    }

    private void handleList() {
        if (storage.isEmpty()) {
            System.out.println("No items in storage.");
            return;
        }
        System.out.println("Items in storage: ");
        System.out.println(storage.toString());
    }

    private void handleSearch() {
        System.out.print("Enter food name to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        for (FoodItem item : ((ds.QueueStorage<FoodItem>) storage).getAll()) {
            if (item.getName().toLowerCase().contains(searchName)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching food found.");
        }
    }
}

}
