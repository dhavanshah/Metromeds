package com.metromeds.app.business.customer.customertiers.tierfactory;

import com.metromeds.app.business.customer.customertiers.tierconfiguration.CustomerTiers;

public interface CustomerTierFactory {
    public CustomerTiers getTierToSet(Integer id);
}
