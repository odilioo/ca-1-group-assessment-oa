package app;

import ds.QueueStorage;
import ds.StorageInterface;
import model.FoodItem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainApp {
    private final StorageInterface storage = new QueueStorage(8);
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MainApp().run();
    }
