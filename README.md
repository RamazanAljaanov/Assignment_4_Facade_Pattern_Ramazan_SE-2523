# Online Order Processing Facade

A Java demonstration of the Facade design pattern. `OrderClient` calls only `OrderFacade`; the facade coordinates inventory, payment, shipping, and notification services.

## Scenarios

- Approved payment: reserve stock, charge the customer, create a shipment, and send confirmation.
- Declined payment: release the reserved stock and return an order with status `FAILED`.

The subsystem services are simplified in-memory examples. Inventory starts with five `BOOK-101` items and five `MUG-202` items. The demo randomly chooses three different cart combinations for each run; the customer payment flag simulates approval or decline; no real payments or shipments are created.

## Requirements

- JDK 17 or newer
- Maven

## Run

From the project root:

```sh
mvn package
java -cp target/classes Assignment4.Main
```

You can also run `Assignment4.Main` from IntelliJ IDEA.
