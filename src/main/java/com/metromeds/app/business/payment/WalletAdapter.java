package com.metromeds.app.business.payment;


import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.models.payment.Payment;
import com.metromeds.app.models.payment.PaymentStatus;
import com.metromeds.app.repository.CustomerRepository;

public class WalletAdapter implements IPayment{
    private CustomerRepository customerRepository;

    public WalletAdapter(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Payment pay(Order order, Bill bill) {
        WalletPayment walletPayment=new WalletPayment(this.customerRepository);
        PaymentStatus result = walletPayment.payViaWallet(order,bill);
        Payment payment = new Payment();
        payment.setPaymentStatus(result.toString());
        return payment;
    }
}
