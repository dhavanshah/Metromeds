package com.metromeds.app.business.bill;
import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.business.discount.DiscountEngine;
import com.metromeds.app.service.converters.systemTimeConverter;

import java.text.ParseException;

public class BillGenerationMemento {
    BillCareTaker billCareTaker = new BillCareTaker();
    BillOriginator billOriginator = new BillOriginator();
    public Bill generateBill(Order order, Long billLength) throws ParseException {
        //Apply Discount
        DiscountEngine discount = new DiscountEngine();
        discount.applyDiscount(order);
        systemTimeConverter systemTime = new systemTimeConverter();
        //Create a new bill object
        billOriginator.billGenerationMemento();
        //Store the internal state
        billCareTaker.setBillMemento(billOriginator.billGenerationMemento());
        billOriginator.setId((int) (billLength + 1));
        billOriginator.setCustomerId(order.getCustomerId());
        billOriginator.setOrderId(order.getId());
        billOriginator.setTotalAmount(order.getAmountPayable() + order.getDeliveryCharge());
        billOriginator.setBillGenerationDate(systemTime.getDateNow());
        return billOriginator.billGenerationMemento();
    }

    public Bill undoBillGeneration() {
        return billOriginator.restoreBillGenerationMemento(billCareTaker.getBillMemento());
    }
}
