package com.metromeds.app.business.customer.customertiers.tierfactory;

import com.metromeds.app.business.customer.customertiers.tierconfiguration.ConfigureGoldTier;
import com.metromeds.app.business.customer.customertiers.tierconfiguration.CustomerTiers;
import com.metromeds.app.repository.CustomerRepository;

public class GoldTierFactory implements CustomerTierFactory {

    CustomerRepository customerRepository;

    public GoldTierFactory(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerTiers getTierToSet(Integer id) {
        return new ConfigureGoldTier(customerRepository);
    }

}
