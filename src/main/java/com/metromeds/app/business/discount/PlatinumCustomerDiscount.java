package com.metromeds.app.business.discount;

public class PlatinumCustomerDiscount implements IDiscount{
    @Override
    public double getDiscount() {
        return 0.1;
    }
}
