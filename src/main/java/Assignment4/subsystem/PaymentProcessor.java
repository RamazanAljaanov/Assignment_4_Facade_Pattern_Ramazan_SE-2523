package Assignment4.subsystem;

import Assignment4.model.Customer;

import java.math.BigDecimal;

public class PaymentProcessor {
    public boolean charge(Customer customer, BigDecimal amount) {
        System.out.printf("Processing payment of $%s for %s...%n", amount, customer.getName());
        return customer.isPaymentApproved();
    }
}
