package model.ds;
/****
 * Help to represents a food item with a production date and a best-before date.
 * The difference between production and best before must not exceed 14 days.
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class FoodItem implements Comparable<FoodItem> {
    private final String name;
    private final double weight;
    private final LocalDate productionDate;
    private final LocalDate bestBeforeDate;
    private final LocalDateTime timePlaced;

    public FoodItem(String name, double weight, LocalDate bestBeforeDate) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(bestBeforeDate, "Best before date cannot be null");
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }

        LocalDate productionDate = LocalDate.now();

        long days = ChronoUnit.DAYS.between(productionDate, bestBeforeDate);
        if (days < 0) {
            throw new IllegalArgumentException("Best before cannot be earlier than production date");
        }
        if (days > 14) {
            throw new IllegalArgumentException("Best before cannot exceed 14 days from production date");
        }

        this.name = name;
        this.weight = weight;
        this.productionDate = productionDate;
        this.bestBeforeDate = bestBeforeDate;
        this.timePlaced = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public LocalDate getBestBeforeDate() {
        return bestBeforeDate;
    }

    public LocalDateTime getTimePlaced() {
        return timePlaced;
    }

    @Override
    public String toString() {
        return String.format("%s (weight: %.2f g, produced: %s, best before: %s, placed: %s)",
                name, weight, productionDate, bestBeforeDate, timePlaced);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodItem)) return false;
        FoodItem foodItem = (FoodItem) o;
        return Double.compare(foodItem.weight, weight) == 0 &&
                name.equals(foodItem.name) &&
                productionDate.equals(foodItem.productionDate) &&
                bestBeforeDate.equals(foodItem.bestBeforeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, productionDate, bestBeforeDate);
    }

    @Override
    public int compareTo(FoodItem other) {
        return this.productionDate.compareTo(other.productionDate);
    }
}
