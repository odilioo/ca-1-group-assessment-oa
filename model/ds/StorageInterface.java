package ds;

import model.FoodItem;

public interface StorageInterface {
    void enqueue(FoodItem item);
    FoodItem dequeue();
    FoodItem peek();
    boolean isFull();
    boolean isEmpty();
    int size();
}
