package com.metromeds.app.business.customer;

import com.metromeds.app.business.customer.customertiers.SetCustomerTier;
import com.metromeds.app.business.customer.customertiers.tierconfiguration.CustomerTiers;
import com.metromeds.app.business.customer.customertiers.tierfactory.CustomerTierFactory;
import com.metromeds.app.models.customer.Customer;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.repository.BillRepository;
import com.metromeds.app.repository.CustomerRepository;
import com.metromeds.app.repository.OrderRepository;

public class CustomerLoyalty {
    static final int LOYALTY_POINTS_APPLICABLE = 10;
    Customer customer;
    Order order;
    CustomerRepository customerRepository;
    BillRepository billRepository;
    OrderRepository orderRepository;

    public CustomerLoyalty(CustomerRepository customerRepository, OrderRepository orderRepository, BillRepository billRepository) {

        this.customerRepository = customerRepository;
        this.billRepository = billRepository;
        this.orderRepository = orderRepository;
    }

    public void updateLoyaltyPoints(Order order, Customer customer) {
        int updatedPoints = (int) (customer.getLoyaltyPoints() + (order.getCartPrice() * LOYALTY_POINTS_APPLICABLE));
        customer.setLoyaltyPoints(updatedPoints);
        customerRepository.save(customer);
    }

    //Code to set the tier through the customer id coming from request and by checking the customer tier
    public void updateCustomerTier(Customer customer) {
        SetCustomerTier setCustomerTier = new SetCustomerTier(customerRepository);

        if (customer.getLoyaltyPoints() >= 200 && customer.getLoyaltyPoints() < 500 && !customer.getCustomerTier().equals("gold")) {
            CustomerTierFactory set = setCustomerTier.configureCustomerTierOf("gold");
            CustomerTiers t1 = set.getTierToSet(customer.getId());
            t1.configureCustomerTier(customer.getId());
        }
        else if (customer.getLoyaltyPoints() >= 500 && customer.getCustomerTier().equals("platinum")) {
            CustomerTierFactory set = setCustomerTier.configureCustomerTierOf("platinum");
            CustomerTiers t1 = set.getTierToSet(customer.getId());
            t1.configureCustomerTier(customer.getId());
        }
    }
}
