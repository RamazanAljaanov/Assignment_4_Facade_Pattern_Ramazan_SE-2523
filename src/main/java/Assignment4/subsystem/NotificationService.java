package Assignment4.subsystem;

import Assignment4.model.Order;

public class NotificationService {
    public void sendConfirmation(Order order) {
        System.out.printf("Confirmation sent to %s for order %s.%n",
                order.getCustomer().getName(), order.getOrderId());
    }
}
