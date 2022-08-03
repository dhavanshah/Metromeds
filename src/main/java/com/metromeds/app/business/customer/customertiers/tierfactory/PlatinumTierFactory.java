package com.metromeds.app.business.customer.customertiers.tierfactory;

import com.metromeds.app.business.customer.customertiers.tierconfiguration.ConfigurePlatinumTier;
import com.metromeds.app.business.customer.customertiers.tierconfiguration.CustomerTiers;
import com.metromeds.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@Description(value = "Concrete implementation of customer platinum tier factory.")
//@Service
public class PlatinumTierFactory implements CustomerTierFactory {
    @Autowired
    CustomerRepository customerRepository;

    public PlatinumTierFactory(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public CustomerTiers getTierToSet(Integer id) {

        return new ConfigurePlatinumTier(customerRepository);
    }
}
