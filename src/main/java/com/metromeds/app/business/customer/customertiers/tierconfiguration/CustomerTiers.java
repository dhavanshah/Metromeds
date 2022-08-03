package com.metromeds.app.business.customer.customertiers.tierconfiguration;

import org.springframework.context.annotation.Description;

@Description(value = "Definition of tier configuration method")
public interface CustomerTiers {
    public void configureCustomerTier(Integer id);
}