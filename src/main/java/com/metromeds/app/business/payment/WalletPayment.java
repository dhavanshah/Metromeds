package com.metromeds.app.business.payment;
import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.customer.Customer;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.models.payment.PaymentStatus;
import com.metromeds.app.repository.CustomerRepository;

//Adaptee
public class WalletPayment {

    private CustomerRepository customerRepository;
    private Customer customer;

    public WalletPayment(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    private void setCustomer(int customerId){
        customer = this.customerRepository.findOne(customerId);
    }

    public void deductWalletBalance(double amount){
        Double walletBalance = customer.getWalletBalance() - amount;
        customer.setWalletBalance(walletBalance);
    }

    public PaymentStatus payViaWallet(Order order, Bill bill){
        setCustomer(order.getCustomerId());
        deductWalletBalance(bill.getTotalAmount());
        this.customerRepository.save(customer);
        return PaymentStatus.SUCCESS;
    }
}
