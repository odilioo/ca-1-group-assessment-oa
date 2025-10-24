package ds;

import model.FoodItem;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * FIFO implementation using LinkedList with limited capacity.
 */
public class QueueStorage<T> implements StorageInterface<T> {
    private final Queue<T> queue;
    private final int capacity;

    public QueueStorage(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0");
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public QueueStorage() {
        this(8); // default capacity 8
    }

    @Override
    public synchronized void enqueue(T item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        if (isFull())
            throw new IllegalStateException("Storage is full");
        queue.add(item);
    }

    @Override
    public synchronized T dequeue() {
        T it = queue.poll();
        if (it == null)
            throw new NoSuchElementException("Storage is empty");
        return it;
    }

    @Override
    public synchronized T peek() {
        T it = queue.peek();
        if (it == null)
            throw new NoSuchElementException("Storage is empty");
        return it;
    }

    @Override
    public synchronized boolean isFull() {
        return queue.size() >= capacity;
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public synchronized java.util.Queue<T> getAll() {
        return new java.util.LinkedList<>(queue);
    }
}