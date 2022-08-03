package com.metromeds.app.business.payment;

import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.models.payment.Payment;

public class PayPalAdapter implements IPayment{

    @Override
    public Payment pay(Order order, Bill bill) {
        return null;
    }
}
