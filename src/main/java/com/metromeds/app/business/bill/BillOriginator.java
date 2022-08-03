package com.metromeds.app.business.bill;

import com.metromeds.app.models.bill.Bill;

import java.util.Date;

public class BillOriginator {
    private Integer id;
    private Integer orderId;
    private double totalAmount;
    private Integer customerId;
    private Date billGenerationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getBillGenerationDate() {
        return billGenerationDate;
    }

    public void setBillGenerationDate(Date billGenerationDate) {
        this.billGenerationDate = billGenerationDate;
    }

    // Stores memento
    public Bill billGenerationMemento(){
        return new Bill(id, orderId, totalAmount, customerId, billGenerationDate);
    }

    // Restore memento
    public Bill restoreBillGenerationMemento(Bill billMemento){
        this.setId(billMemento.getId());
        this.setOrderId(billMemento.getOrderId());
        this.setCustomerId(billMemento.getCustomerId());
        this.setCustomerId(billMemento.getCustomerId());
        this.setBillGenerationDate(billMemento.getBillGenerationDate());
        return billMemento;
    }
}