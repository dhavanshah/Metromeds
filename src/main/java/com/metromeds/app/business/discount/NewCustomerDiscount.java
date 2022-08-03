package com.metromeds.app.business.discount;

public class NewCustomerDiscount implements IDiscount{
    @Override
    public double getDiscount() {
        return 0.03;
    }
}
