package com.metromeds.app.business.customer.customertiers.tierconfiguration;
import com.metromeds.app.business.customer.CustomerController;
import com.metromeds.app.models.customer.Customer;
import com.metromeds.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfigureGoldTier implements CustomerTiers {
    @Autowired
    CustomerController customerController;
    @Autowired
    private CustomerRepository customerRepository;

    public ConfigureGoldTier(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public void configureCustomerTier(Integer id) {
        Customer c1 = customerRepository.findOne(id);
        c1.setCustomerTier("gold");
        customerRepository.save(c1);
    }
}
