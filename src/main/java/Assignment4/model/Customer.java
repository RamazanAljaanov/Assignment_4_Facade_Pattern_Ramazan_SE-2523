package Assignment4.model;

import java.util.Objects;

public class Customer {
    private final String id;
    private final String name;
    private final String shippingAddress;
    private final boolean paymentApproved;

    public Customer(String id, String name, String shippingAddress, boolean paymentApproved) {
        this.id = requireText(id, "Customer ID");
        this.name = requireText(name, "Customer name");
        this.shippingAddress = requireText(shippingAddress, "Shipping address");
        this.paymentApproved = paymentApproved;
    }

    private static String requireText(String value, String label) {
        Objects.requireNonNull(value, label + " is required.");
        if (value.isBlank()) {
            throw new IllegalArgumentException(label + " cannot be blank.");
        }
        return value;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getShippingAddress() { return shippingAddress; }
    public boolean isPaymentApproved() { return paymentApproved; }
}
