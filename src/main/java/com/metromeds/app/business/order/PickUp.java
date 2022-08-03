package com.metromeds.app.business.order;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import com.metromeds.app.business.order.deliveryinfo.CountyCharges;
import com.metromeds.app.models.order.Order;

import java.text.SimpleDateFormat;

public class PickUp implements OrderPacking {

    CountyCharges info = new CountyCharges();


    @Override
    public void getCost(Order order) {
        //Implementation not needed
    }

    @Override
    public void getProduct(Order order) {
        //Implementation not needed
    }

    @Override
    public void getShippingFee(Order order) {
        String county = order.getShipCity();
        String service = order.getServiceType();
        HashMap<String, Long> map;
        if (service.equals("Express"))
            map = info.getExpressDeliveryFee();
        else
            map = info.getStandardDeliveryFee();
        Long cost = map.get(county);
        order.setShippingFee(cost);
    }

    @Override
    public void getDeliveryDate(Order order) throws ParseException {
        String county = order.getShipCity();
        String service = order.getServiceType();
        HashMap<String, String> map;
        if (service.equals("Express"))
            map = info.getExpressDeliveryDate();
        else
            map = info.getStandardDeliveryDate();
        String date = map.get(county);
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        order.setShippedDate(date1);
    }
}

