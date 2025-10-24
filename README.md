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
=== FastFoodFIFO - Console ===
1) Add item (enqueue)
2) Remove item (dequeue)
3) View next item (peek)
4) List all items
5) Exit

Choose: 1
Item name: Burger
Production date (YYYY-MM-DD): 2025-10-24
Best-before date (YYYY-MM-DD): 2025-10-30
Added: FoodItem{name='Burger', produced=2025-10-24, bestBefore=2025-10-30}
```

## 💡 Concepts Demonstrated
- Java **Generics**
- **Queue** data structure (FIFO)
- **Swing GUI** design
- **Exception handling**
- **Code reusability** with interfaces


## 💡  AI Usage Declaration:

-AI tools such as ChatGPT were used to help clarify Java concepts, improve documentation structure, and assist with code formatting.
All final code, testing, and debugging decisions were made by the project authors (Odilio de Oliveira and Aline Aparecida).

## 👥 Authors
- **Odilio de Oliveira**
- **Aline Aparecida**

