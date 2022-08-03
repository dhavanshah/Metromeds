package com.metromeds.app.business.discount;

public class GoldCustomerDiscount implements IDiscount {
    @Override
    public double getDiscount() {
        return 0.09;
    }
}
