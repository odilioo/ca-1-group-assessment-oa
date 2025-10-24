package app;

import javax.swing.*;                // For JFrame, JButton, JTextField, JTextArea, JLabel, JScrollPane
import java.awt.*;                  // For layouts, EventQueue, etc.
import java.awt.event.ActionEvent;  // For ActionEvent used in button handlers
import java.time.LocalDate;         // For production and best-before dates
import java.time.format.DateTimeParseException; // For date format validation
import java.util.NoSuchElementException;        // For empty queue handling

import ds.StorageInterface;         // Your interface for storage
import ds.QueueStorage;             // Your queue implementation
import model.ds.FoodItem;             // Your food item class

public class SimpleSwingApp extends JFrame {

    private StorageInterface<FoodItem> storage;
    private JTextArea outputArea;

    public SimpleSwingApp() {
        storage = new QueueStorage<>();
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        setupUI();
    }

    private void setupUI() {
        setTitle("Fast Food FIFO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton peekBtn = new JButton("Peek");
        JButton listBtn = new JButton("List");
        JButton searchBtn = new JButton("Search");

        addBtn.addActionListener(this::onAdd);
        removeBtn.addActionListener(this::onRemove);
        peekBtn.addActionListener(this::onPeek);
        listBtn.addActionListener(this::onList);
        searchBtn.addActionListener(this::onSearch);

        add(addBtn);
        add(removeBtn);
        add(peekBtn);
        add(listBtn);
        add(searchBtn);
        add(new JScrollPane(outputArea));
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onAdd(ActionEvent e) {
        try {
            // Define strictly allowed fast-food items (Pizza added)
            String[] validItems = {"Burger", "Fries", "Drink", "Salad", "Wrap", "Nuggets", "Hotdog", "Pizza"};
            String name = JOptionPane.showInputDialog(this, "Enter food item (Burger, Fries, Drink, etc.):");

            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Food name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            name = name.trim();

            // Validate if the item is one of the allowed fast-food items
            boolean isValid = false;
            for (String valid : validItems) {
                if (valid.equalsIgnoreCase(name)) {
                    isValid = true;
                    name = valid; // normalize capitalization
                    break;
                }
            }

            if (!isValid) {
                JOptionPane.showMessageDialog(this,
                        "❌ Invalid item. Allowed items are:\n" + String.join(", ", validItems),
                        "Invalid Item", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String weightStr = JOptionPane.showInputDialog(this, "Enter weight (grams):");
            if (weightStr == null || weightStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Weight cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double weight;
            try {
                weight = Double.parseDouble(weightStr.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid weight value. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (weight <= 0) {
                JOptionPane.showMessageDialog(this, "❌ Invalid weight. It must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if storage is full
            if (storage.isFull()) {
                JOptionPane.showMessageDialog(this, "⚠️ Storage is full. Cannot add more items.", "Storage Full", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Auto-assign best-before date based on food type
            LocalDate bestBefore;
            if (name.equalsIgnoreCase("Burger") || name.equalsIgnoreCase("Fries") || name.equalsIgnoreCase("Hotdog")) {
                bestBefore = LocalDate.now().plusDays(1);
            } else if (name.equalsIgnoreCase("Drink") || name.equalsIgnoreCase("Salad")) {
                bestBefore = LocalDate.now().plusDays(3);
            } else {
                bestBefore = LocalDate.now().plusDays(5);
            }

            FoodItem item = new FoodItem(name, weight, bestBefore);
            storage.enqueue(item);

            append("Added: " + item);
            JOptionPane.showMessageDialog(this,
                    "✅ Item added successfully!\n" +
                    "Item: " + name + "\nWeight: " + weight + "g\nBest before: " + bestBefore,
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, "Storage is full. Cannot add more items.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onRemove(ActionEvent e) {
        FoodItem removed = storage.dequeue();
        if (removed == null) {
            append("Storage is empty.");
        } else {
            append("Removed: " + removed);
        }
    }

    private void onPeek(ActionEvent e) {
        FoodItem next = storage.peek();
        if (next == null) {
            append("Storage is empty.");
        } else {
            append("Next item: " + next);
        }
    }

    private void onList(ActionEvent e) {
        if (storage.isEmpty()) {
            append("No items in storage.");
            return;
        }
        append("Items in storage:\n" + storage.toString());
    }

    private void append(String s) {
        outputArea.append(s + "\n");
    }

    private void onSearch(ActionEvent e) {
        String searchName = JOptionPane.showInputDialog(this, "Enter food name to search:");
        if (searchName == null || searchName.trim().isEmpty()) return;
        boolean found = false;
        StringBuilder results = new StringBuilder();
        for (FoodItem item : ((ds.QueueStorage<FoodItem>) storage).getAll()) {
            if (item.getName().toLowerCase().contains(searchName.toLowerCase())) {
                results.append(item).append("\n");
                found = true;
            }
        }
        if (found) {
            append("Search results:\n" + results);
            JOptionPane.showMessageDialog(this, "Results found:\n" + results);
        } else {
            append("No matching food found for: " + searchName);
            JOptionPane.showMessageDialog(this, "No matching food found for: " + searchName);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(SimpleSwingApp::new);
    }
}
