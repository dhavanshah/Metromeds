package com.metromeds.app.business.discount;

public class SilverCustomerDiscount implements IDiscount{
    @Override
    public double getDiscount() {
        return 0.05;
    }
}
