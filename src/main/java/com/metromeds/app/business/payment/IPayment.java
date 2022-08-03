package com.metromeds.app.business.payment;
import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.models.payment.Payment;

//Target Interface
public interface IPayment {
    Payment pay(Order order, Bill bill) ;
}
