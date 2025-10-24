package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Help to represents a food item with a production date and a best-before date.
 * The difference between production and best before must not exceed 14 days.
 */
public class FoodItem {
    private final String name;
    private final LocalDate productionDate;
    private final LocalDate bestBeforeDate;

    public FoodItem(String name, LocalDate productionDate, LocalDate bestBeforeDate) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(productionDate, "Production date cannot be null");
        Objects.requireNonNull(bestBeforeDate, "Best before date cannot be null");

        long days = ChronoUnit.DAYS.between(productionDate, bestBeforeDate);
        if (days < 0) {
            throw new IllegalArgumentException("Best before cannot be earlier than production date");
        }
        if (days > 14) {
            throw new IllegalArgumentException("Best before cannot exceed 14 days from production date");
        }

        this.name = name;
        this.productionDate = productionDate;
        this.bestBeforeDate = bestBeforeDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public LocalDate getBestBeforeDate() {
        return bestBeforeDate;
    }

    @Override
    public String toString() {
        return String.format("%s (produced: %s, best before: %s)", name, productionDate, bestBeforeDate);
    }
}