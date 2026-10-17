package Assignment4.subsystem;

import Assignment4.model.Cart;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> availableStock = new HashMap<>();

    public InventoryService() {
        availableStock.put("BOOK-101", 5);
        availableStock.put("MUG-202", 5);
        availableStock.put("Notebook-112", 5);
    }

    public void reserveItems(Cart cart) {
        Map<String, Integer> requested = quantitiesByProduct(cart);
        for (Map.Entry<String, Integer> entry : requested.entrySet()) {
            int available = availableStock.getOrDefault(entry.getKey(), 0);
            if (available < entry.getValue()) {
                throw new IllegalStateException("Insufficient stock for product " + entry.getKey() + ".");
            }
        }
        requested.forEach((productId, quantity) ->
                availableStock.put(productId, availableStock.get(productId) - quantity));
    }

    public void releaseItems(Cart cart) {
        quantitiesByProduct(cart).forEach((productId, quantity) -> {
            int updatedStock = availableStock.merge(productId, quantity, Integer::sum);
            System.out.printf("Rollback: released %d unit(s) of %s; available stock is now %d.%n",
                    quantity, productId, updatedStock);
        });
    }

    public int getAvailableStock(String productId) {
        return availableStock.getOrDefault(productId, 0);
    }

    private Map<String, Integer> quantitiesByProduct(Cart cart) {
        Map<String, Integer> quantities = new HashMap<>();
        for (Cart.Item item : cart.getItems()) {
            quantities.merge(item.productId(), item.quantity(), Integer::sum);
        }
        return quantities;
    }
}
