package Assignment4.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final BigDecimal total;
    private OrderStatus status;
    private String trackingNumber;
    private String failureReason;

    public Order(String orderId, Customer customer, BigDecimal total) {
        this.orderId = Objects.requireNonNull(orderId, "Order ID is required.");
        this.customer = Objects.requireNonNull(customer, "Customer is required.");
        this.total = Objects.requireNonNull(total, "Order total is required.");
        if (total.signum() < 0) {
            throw new IllegalArgumentException("Order total cannot be negative.");
        }
        this.status = OrderStatus.CREATED;
    }

    public void markPaid() {
        requireStatus(OrderStatus.CREATED);
        status = OrderStatus.PAID;
    }

    public void markShipped(String trackingNumber) {
        requireStatus(OrderStatus.PAID);
        this.trackingNumber = Objects.requireNonNull(trackingNumber, "Tracking number is required.");
        status = OrderStatus.SHIPPED;
    }

    public void markFailed(String reason) {
        if (status == OrderStatus.SHIPPED) {
            throw new IllegalStateException("A shipped order cannot be marked as failed.");
        }
        failureReason = Objects.requireNonNull(reason, "Failure reason is required.");
        status = OrderStatus.FAILED;
    }

    private void requireStatus(OrderStatus expected) {
        if (status != expected) {
            throw new IllegalStateException("Expected order status " + expected + " but was " + status + ".");
        }
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public BigDecimal getTotal() { return total; }
    public OrderStatus getStatus() { return status; }
    public String getTrackingNumber() { return trackingNumber; }
    public String getFailureReason() { return failureReason; }
}
