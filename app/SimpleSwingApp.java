package app;

import javax.swing.*;                // For JFrame, JButton, JTextField, JTextArea, JLabel, JScrollPane
import java.awt.*;                  // For layouts, EventQueue, etc.
import java.awt.event.ActionEvent;  // For ActionEvent used in button handlers
import java.time.LocalDate;         // For production and best-before dates
import java.time.format.DateTimeParseException; // For date format validation
import java.util.NoSuchElementException;        // For empty queue handling

import ds.StorageInterface;         // Your interface for storage
import ds.QueueStorage;             // Your queue implementation
import model.FoodItem;              // Your food item class

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
        String name = JOptionPane.showInputDialog(this, "Enter food name:");
        if (name != null && !name.trim().isEmpty()) {
            String weightStr = JOptionPane.showInputDialog(this, "Enter weight (grams):");
            if (weightStr == null || weightStr.trim().isEmpty()) return;
            double weight = Double.parseDouble(weightStr);
            FoodItem item = new FoodItem(name, weight, LocalDate.now(), LocalDate.now().plusDays(5));
            storage.enqueue(item);
            append("Added: " + item);
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
        } else {
            append("No matching food found for: " + searchName);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(SimpleSwingApp::new);
    }
}
}
