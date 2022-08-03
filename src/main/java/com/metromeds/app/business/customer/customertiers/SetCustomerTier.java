package com.metromeds.app.business.customer.customertiers;
import com.metromeds.app.business.customer.customertiers.tierfactory.CustomerTierFactory;
import com.metromeds.app.business.customer.customertiers.tierfactory.GoldTierFactory;
import com.metromeds.app.business.customer.customertiers.tierfactory.PlatinumTierFactory;
import com.metromeds.app.business.customer.customertiers.tierfactory.SilverTierFactory;
import com.metromeds.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SetCustomerTier {
    @Autowired
    CustomerRepository customerRepository;

    public SetCustomerTier(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerTierFactory configureCustomerTierOf(String tierType) {
            CustomerTierFactory customerTierFactory = null;
            switch (tierType) {
                case "silver":
                    customerTierFactory = new SilverTierFactory(customerRepository);
                    break;
                case "gold":

                    customerTierFactory = new GoldTierFactory(customerRepository);
                    break;
                case "platinum":
                    customerTierFactory = new PlatinumTierFactory(customerRepository);
                    break;
                default:
                    break;
            }
        return customerTierFactory;
    }

}
