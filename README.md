# 🥪 FastFoodFIFO

A simple Java application that simulates a **First-In, First-Out (FIFO)** food storage system — ideal for learning **queues**, **generics**, and **Swing GUI** development.

## 🚀 Features
- **Console App (MainApp.java)**
  - Add (enqueue) new food items
  - Remove (dequeue) the oldest item
  - Peek at the next item
  - List all stored items
  - Search for food items by name, weight range, or best-before date range

- **Graphical App (SimpleSwingApp.java)**
  - Add food items interactively
  - View, remove, and list stored items
  - Scrollable display and clean layout

- **Generic Queue Implementation**
  - `StorageInterface<T>` — defines a generic storage interface
  - `QueueStorage<T>` — linked-list–based FIFO queue with capacity control

## 🔍 Search Features
The application supports multiple search options:
- **By Name:** Find items that contain a given name or partial text.
- **By Weight:** Search for items within a weight range (e.g., 100g–500g).
- **By Best-Before Date Range:** Search for food items with best-before dates within a specific time frame.

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


## 🧠 Time Complexity
| Operation | Complexity | Explanation |
|------------|-------------|-------------|
| Enqueue | O(1) | Add to end of queue |
| Dequeue | O(1) | Remove from front of queue |
| Peek | O(1) | Access front element |
| Search | O(n) | Iterate through all elements |
| List | O(n) | Traverse all elements |


## 👥 Authors
- **Odilio de Oliveira**
- **Aline Aparecida**


## 🤖 AI Usage Declaration
AI tools (e.g., ChatGPT) were used to assist with code explanations, debugging, and documentation structure.  
All final code, design, and testing were completed by the authors.

