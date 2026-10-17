package Assignment4.facade;

import Assignment4.model.Cart;
import Assignment4.model.Customer;
import Assignment4.model.Order;
import Assignment4.subsystem.InventoryService;
import Assignment4.subsystem.NotificationService;
import Assignment4.subsystem.PaymentProcessor;
import Assignment4.subsystem.ShippingService;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderFacade {
    private final InventoryService inventoryService = new InventoryService();
    private final PaymentProcessor paymentProcessor = new PaymentProcessor();
    private final ShippingService shippingService = new ShippingService();
    private final NotificationService notificationService = new NotificationService();
    private final AtomicInteger nextOrderNumber = new AtomicInteger(1);

    public Order placeOrder(Cart cart, Customer customer) {
        Objects.requireNonNull(cart, "Cart is required.");
        Objects.requireNonNull(customer, "Customer is required.");
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cannot place an order with an empty cart.");
        }

        Order order = new Order("ORD-%04d".formatted(nextOrderNumber.getAndIncrement()),
                customer, cart.getTotal());
        try {
            inventoryService.reserveItems(cart);
        } catch (IllegalStateException exception) {
            order.markFailed(exception.getMessage());
            return order;
        }

        if (!paymentProcessor.charge(customer, order.getTotal())) {
            inventoryService.releaseItems(cart);
            order.markFailed("Payment was declined; reserved stock was released.");
            return order;
        }

        order.markPaid();
        String trackingNumber = shippingService.createShipment(order);
        order.markShipped(trackingNumber);
        notificationService.sendConfirmation(order);
        return order;
    }
}
