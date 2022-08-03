package com.metromeds.app.business.bill;

import com.metromeds.app.models.bill.Bill;

public class BillCareTaker {
    private Bill billMemento;

    public Bill getBillMemento() {
        return billMemento;
    }

    public void setBillMemento(Bill billMemento) {
        this.billMemento = billMemento;
    }

}