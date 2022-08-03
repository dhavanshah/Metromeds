package com.metromeds.app.business.order;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import com.metromeds.app.business.order.deliveryinfo.CountyCharges;
import com.metromeds.app.models.order.Order;

public class Delivery implements OrderPacking{
    CountyCharges info = new CountyCharges();
    public void getShippingFee(Order order)
    {
     HashMap<String, Long> map;
     if(order.getServiceType().equals("Express"))
         map = info.getExpressDeliveryFee();
     else
         map  = info.getStandardDeliveryFee();
     Long cost = map.get(order.getShipCity());
     order.setShippingFee(cost);
    }

    @Override
    public void getDeliveryDate(Order order) throws ParseException {
        HashMap<String, String> map;
        if(order.getServiceType().equals("Express"))
            map = info.getExpressDeliveryDate();
        else
            map  = info.getStandardDeliveryDate();
        String date = map.get(order.getShipCity());
        Date formattedDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
        order.setShippedDate(formattedDate);
    }
    public void getCost(Order order)
    {
        //Implementation not needed
    }

    @Override
    public void getProduct(Order order) {
        //Implementation not needed
    }

}
