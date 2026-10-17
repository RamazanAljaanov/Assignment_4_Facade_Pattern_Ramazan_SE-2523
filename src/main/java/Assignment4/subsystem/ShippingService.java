package Assignment4.subsystem;

import Assignment4.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ShippingService {
    private final AtomicInteger nextTrackingNumber = new AtomicInteger(1000);
    private final Map<String, Shipment> shipments = new HashMap<>();

    public String createShipment(Order order) {
        String trackingNumber = "TRK-" + nextTrackingNumber.getAndIncrement();
        Shipment shipment = new Shipment(
                order.getOrderId(),
                order.getCustomer().getShippingAddress(),
                trackingNumber
        );
        shipments.put(trackingNumber, shipment);

        System.out.printf("Shipment %s created for order %s; destination: %s.%n",
                shipment.trackingNumber(), shipment.orderId(), shipment.shippingAddress());
        return trackingNumber;
    }

    public Shipment getShipment(String trackingNumber) {
        return shipments.get(trackingNumber);
    }

    public record Shipment(String orderId, String shippingAddress, String trackingNumber) {
    }
}
