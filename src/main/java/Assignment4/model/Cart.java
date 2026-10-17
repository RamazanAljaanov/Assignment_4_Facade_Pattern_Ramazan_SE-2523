package Assignment4.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cart {
    private final List<Item> items = new ArrayList<>();

    public void addItem(String productId, String productName, int quantity, BigDecimal unitPrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (unitPrice == null || unitPrice.signum() < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative or null.");
        }
        items.add(new Item(productId, productName, quantity, unitPrice));
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(item -> item.unitPrice().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public record Item(String productId, String productName, int quantity, BigDecimal unitPrice) {
        public Item {
            Objects.requireNonNull(productId, "Product ID is required.");
            Objects.requireNonNull(productName, "Product name is required.");
            Objects.requireNonNull(unitPrice, "Unit price is required.");
            if (productId.isBlank() || productName.isBlank()) {
                throw new IllegalArgumentException("Product ID and name cannot be blank.");
            }
            if (quantity <= 0 || unitPrice.signum() < 0) {
                throw new IllegalArgumentException("Quantity must be positive and price cannot be negative.");
            }
        }
    }
}
