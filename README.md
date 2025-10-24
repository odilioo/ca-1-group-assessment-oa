# 🥪 FastFoodFIFO

A simple Java application that simulates a **First-In, First-Out (FIFO)** food storage system — ideal for learning **queues**, **generics**, and **Swing GUI** development.

## 🚀 Features
- **Console App (MainApp.java)**
  - Add (enqueue) new food items
  - Remove (dequeue) the oldest item
  - Peek at the next item
  - List all stored items

- **Graphical App (SimpleSwingApp.java)**
  - Add food items interactively
  - View, remove, and list stored items
  - Scrollable display and clean layout

- **Generic Queue Implementation**
  - `StorageInterface<T>` — defines a generic storage interface
  - `QueueStorage<T>` — linked-list–based FIFO queue with capacity control

## 🧩 Project Structure
```
FastFoodFIFO/
├── app/
│   ├── MainApp.java           # Console version
│   └── SimpleSwingApp.java    # GUI version
├── ds/
│   ├── StorageInterface.java  # Generic interface
│   └── QueueStorage.java      # FIFO implementation
└── model/
    └── FoodItem.java          # Data class for food items
```

## 🛠️ Requirements
- Java 17+
- IntelliJ IDEA (recommended) or any Java IDE
- No external dependencies

## ▶️ How to Run

### Console App
```bash
javac app/MainApp.java
java app.MainApp
```

### GUI App
```bash
javac app/SimpleSwingApp.java
java app.SimpleSwingApp
```

## 📦 Example Usage (Console)
```
=== FastFoodFIFO - Menu ===
1) Add item 
2) Remove item
3) View next item
4) List all items
5) Search item by name
6) Exit

Choose: 1
Item name: Burger
Item weight (grams): 200
Production date automatically set to: 2025-10-24
Best before date (YYYY-MM-DD): 2025-10-31
Added: Burger (weight: 200,00 g, produced: 2025-10-24, best before: 2025-10-31, placed: 2025-10-24T23:06:47.554884400)
```

## 💡 Concepts Demonstrated
- Java **Generics**
- **Queue** data structure (FIFO)
- **Swing GUI** design
- **Exception handling**
- **Code reusability** with interfaces

## 👥 Authors
- **Odilio de Oliveira**
- **Aline de Oliveira**
