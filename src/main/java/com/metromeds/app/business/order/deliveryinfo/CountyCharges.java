package com.metromeds.app.business.order.deliveryinfo;

import java.util.HashMap;

public class CountyCharges {
    HashMap<String, Long> standardDeliveryFee = new HashMap<>();
    HashMap<String, Long> expressDeliveryFee = new HashMap<>();
    HashMap<String, String> expressDeliveryDate = new HashMap<>();
    HashMap<String, String> standardDeliveryDate = new HashMap<>();
    static final String DUBLIN = "Dublin";
    static final String LIMERICK = "Limerick";
    static final String CORK = "Cork";
    static final String CONST_DATE = "15/04/2021";

    public CountyCharges() {
        buildExpressDeliveryFeeMap();
        buildStandardDeliveryFeeMap();
        buildExpressDeliveryDateMap();
        buildStandardDeliveryDateMap();

    }

    public void buildExpressDeliveryFeeMap() {
        expressDeliveryFee.put(DUBLIN, (long) 7.50);
        expressDeliveryFee.put(LIMERICK, (long) 5.50);
        expressDeliveryFee.put(CORK, (long) 6.00);

    }

    public void buildStandardDeliveryFeeMap() {
        standardDeliveryFee.put(DUBLIN, (long) 5.00);
        standardDeliveryFee.put(LIMERICK, (long) 3.50);
        standardDeliveryFee.put(CORK, (long) 4.00);
    }

    public void buildExpressDeliveryDateMap() {
        expressDeliveryDate.put(DUBLIN, CONST_DATE);
        expressDeliveryDate.put(LIMERICK, CONST_DATE);
        expressDeliveryDate.put(CORK, CONST_DATE);
    }

    public void buildStandardDeliveryDateMap() {
        standardDeliveryDate.put(DUBLIN, CONST_DATE);
        standardDeliveryDate.put(LIMERICK, CONST_DATE);
        standardDeliveryDate.put(CORK, CONST_DATE);
    }

    public HashMap<String, Long> getExpressDeliveryFee() {
        return expressDeliveryFee;
    }

    public HashMap<String, Long> getStandardDeliveryFee() {
        return standardDeliveryFee;
    }

    public HashMap<String, String> getExpressDeliveryDate() {
        return expressDeliveryDate;
    }

    public HashMap<String, String> getStandardDeliveryDate() {
        return standardDeliveryDate;
    }
}

