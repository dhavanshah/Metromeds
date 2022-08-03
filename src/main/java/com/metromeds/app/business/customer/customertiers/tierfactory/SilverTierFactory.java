package com.metromeds.app.business.customer.customertiers.tierfactory;

import com.metromeds.app.business.customer.customertiers.tierconfiguration.ConfigureSilverTier;
import com.metromeds.app.business.customer.customertiers.tierconfiguration.CustomerTiers;
import com.metromeds.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SilverTierFactory implements CustomerTierFactory {
    @Autowired
    CustomerRepository customerRepository;
    public SilverTierFactory(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerTiers getTierToSet(Integer id) {
        return new ConfigureSilverTier(customerRepository);
    }
}
