package com.metromeds.app.models.bill;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    private Integer Id;
    private Integer orderId;
    private double totalAmount;
    private Integer customerId;
    private Date billGenerationDate;

    public Bill(Integer id, Integer orderId, double totalAmount, Integer customerId, Date billGenerationDate) {
        this.setId(id);
        this.setOrderId(orderId);
        this.setTotalAmount(totalAmount);
        this.setCustomerId(customerId);
        this.setBillGenerationDate(billGenerationDate);
    }

    public Bill() {

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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


}
