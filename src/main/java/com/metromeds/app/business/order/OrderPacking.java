package com.metromeds.app.business.order;

import java.text.ParseException;

import com.metromeds.app.models.order.Order ;
public interface OrderPacking {
    public void getCost(Order order);
    public void getProduct(Order order);
    public void getShippingFee(Order order);
    public void getDeliveryDate(Order order) throws ParseException;
}
