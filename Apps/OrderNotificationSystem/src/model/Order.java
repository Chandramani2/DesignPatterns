package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private final String orderId;
    private final String customerId;
    private final String sellerId;
    private final String deliveryPartnerId;
}