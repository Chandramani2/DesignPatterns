package strategy.payment;

import enums.PaymentType;



public class PaymentStrategyFactory {

    public static PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
        return switch(paymentType){
            case PaymentType.CARD -> new CardPaymentStrategy();
            case PaymentType.UPI ->  new UpiPaymentStrategy();
        };
    }
}
