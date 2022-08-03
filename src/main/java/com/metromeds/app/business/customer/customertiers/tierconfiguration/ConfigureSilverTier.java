package com.metromeds.app.business.customer.customertiers.tierconfiguration;


import com.metromeds.app.business.customer.CustomerController;
import com.metromeds.app.models.customer.Customer;
import com.metromeds.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfigureSilverTier implements CustomerTiers {
    @Autowired
    CustomerController customerController;
    Customer customer;
    CustomerRepository customerRepository;

    public ConfigureSilverTier(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public void configureCustomerTier(Integer id) {
        Customer c1 = customerRepository.findOne(id);
        customer.setCustomerTier("silver");
        customerRepository.save(c1);
    }
}
