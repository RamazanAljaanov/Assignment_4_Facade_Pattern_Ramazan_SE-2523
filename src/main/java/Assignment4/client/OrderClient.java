package Assignment4.client;

import Assignment4.facade.OrderFacade;
import Assignment4.model.Cart;
import Assignment4.model.Customer;
import Assignment4.model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderClient {
    private final OrderFacade orderFacade;

    public OrderClient(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public void demonstrateOrderProcessing() {
        List<Cart> differentCarts = createDifferentSampleCarts();

        Customer approvedCustomer = new Customer(
                "C-100", "Amina", "Baurzhan-Momyshuly 45 Dom 2", true);
        displayResult(orderFacade.placeOrder(differentCarts.get(0), approvedCustomer));

        Customer declinedCustomer = new Customer(
                "C-200", "Daniyar", "34 Uly Batyr", false);
        displayResult(orderFacade.placeOrder(differentCarts.get(1), declinedCustomer));

        Customer paidCustomer = new Customer(
                "E-871", "Ramazan", "Jenis - 3", true);
        displayResult(orderFacade.placeOrder(differentCarts.get(2), paidCustomer));

    }

    private List<Cart> createDifferentSampleCarts() {
        List<Cart> carts = new ArrayList<>(List.of(
                sampleCart(1, 1),
                sampleCart(1, 2),
                sampleCart(2, 1),
                sampleCart(2, 2)
        ));
        Collections.shuffle(carts);
        return carts.subList(0, 3);
    }

    private Cart sampleCart(int bookQuantity, int mugQuantity) {
        Cart cart = new Cart();
        cart.addItem("BOOK-101", "Design Patterns Book", bookQuantity, new BigDecimal("24.99"));
        cart.addItem("MUG-202", "Java Mug", mugQuantity, new BigDecimal("12.50"));
        return cart;
    }

    private void displayResult(Order order) {
        System.out.printf("Order %s: %s, total $%s%n",
                order.getOrderId(), order.getStatus(), order.getTotal());
        if (order.getTrackingNumber() != null) {
            System.out.println("Tracking number: " + order.getTrackingNumber());
        }
        if (order.getFailureReason() != null) {
            System.out.println("Reason: " + order.getFailureReason());
        }
        System.out.println();
    }
}
