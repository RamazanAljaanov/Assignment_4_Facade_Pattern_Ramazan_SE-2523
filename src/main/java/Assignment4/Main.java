package Assignment4;

import Assignment4.client.OrderClient;
import Assignment4.facade.OrderFacade;

public class Main {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        OrderClient client = new OrderClient(orderFacade);
        client.demonstrateOrderProcessing();
    }
}
